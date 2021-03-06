package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.tuition.TuitionDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionInSemesterStudentService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionPendingStudentService;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllTuitionInSemesterServiceImpl extends AbstractBaseService<IFindAllTuitionInSemesterStudentService.Input, List<TuitionDto>>
        implements IFindAllTuitionInSemesterStudentService {

    private final TuitionFeeRepository tuitionFeeRepository;

    private final UserCourseRepository userCourseRepository;

    private final UserRepository userRepository;

    private final SemesterRepository semesterRepository;

    @Override
    public void preExecute(IFindAllTuitionInSemesterStudentService.Input input) {
        if (input.getSemesterId() == null || semesterRepository.findById(input.getSemesterId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND));
        }
    }

    @Override
    public List<TuitionDto> doing(Input input) {
        List<TuitionDto> result = new ArrayList<>();
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentUserFullNameContainingAndOpenCourseSemesterId(input.getFullName(),
                        input.getSemesterId());
        Map<Long, List<OpenCourse>> map = new HashMap<>();

        studentCourses.forEach(studentCourse -> {
            Long studentId = studentCourse.getStudent().getId();
            if (map.containsKey(studentId)) {
                List<OpenCourse> courses = map.get(studentId);
                List<OpenCourse> list = new ArrayList<>(Arrays.asList(studentCourse.getOpenCourse()));
                list.addAll(courses);
                map.put(studentId, list);
            } else {
                map.put(studentId, List.of(studentCourse.getOpenCourse()));
            }
        });
        Set<Long> set = map.keySet();
        for (Long studentId : set) {
            TuitionDto item = new TuitionDto();
            List<OpenCourse> openCourses = map.get(studentId);
            List<TuitionFee> tuitionFees = tuitionFeeRepository
                    .findAllByStudentIdAndSemesterIdAndStatus(studentId, input.getSemesterId(), EStatus.COMPLETED);
            item.setStudentId(studentId);
            item.setFullName(userRepository.findById(studentId).get().getFullName());
            item.setTotalCreditInSemester(openCourses.stream().mapToLong(value -> value.getCourse().getCreditQuantity()).sum());
            AtomicReference<Double> totalFee = new AtomicReference<>(0d);
            openCourses.forEach(course->{
                totalFee.set(totalFee.get() + course.getCourse().getPriceBasic() * course.getCourse().getCreditQuantity());
            });
            Double totalCompleted = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
            item.setTotalFeeInSemester(ConvertDoubleToString.convert(totalFee.get()));
            item.setTotalFeePayment(ConvertDoubleToString.convert(totalCompleted));
            item.setTotalFeeDebt(ConvertDoubleToString.convert(totalFee.get() - totalCompleted));
            if (totalCompleted >= totalFee.get()){
                item.setIsCompleted(true);
            }else{
                item.setIsCompleted(false);
            }

            result.add(item);
        }
        return result;
    }
}
