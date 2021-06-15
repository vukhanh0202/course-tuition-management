package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentFeeDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindAllFeeStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class FindAllFeeStudentServiceImpl extends AbstractBaseService<Long, List<StudentFeeDto>>
        implements IFindAllFeeStudentService<Long, List<StudentFeeDto>> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Override
    public void preExecute(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
    }

    @Override
    public List<StudentFeeDto> doing(Long id) {
        List<StudentFeeDto> result = new ArrayList<>();
        List<StudentCourse> studentCourses = userCourseRepository.findAllByStudentId(id);
        Map<Long, List<StudentCourse>> map = new HashMap<>();
        studentCourses.forEach(studentCourse -> {
            Long semesterId = studentCourse.getOpenCourse().getSemester().getId();
            if (map.containsKey(semesterId)) {
                List<StudentCourse> courses = map.get(semesterId);
                courses.add(studentCourse);
            } else {
                map.put(semesterId, List.of(studentCourse));
            }
        });

        Set<Long> set = map.keySet();
        for (Long key : set) {
            Semester semester = semesterRepository.findById(key).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
            List<StudentCourse> list = map.get(key);
            List<TuitionFee> tuitionFees = tuitionFeeRepository.findAllByStudentIdAndSemesterId(id, semester.getId());
            // Sort by timeCompleted DESC
            tuitionFees.sort(new Comparator<TuitionFee>() {
                @Override
                public int compare(TuitionFee o1, TuitionFee o2) {
                    // sort DESC
                    if (o2.getTimeCompleted().after(o1.getTimeCompleted())) {
                        return 1;
                    } else if (o1.getTimeCompleted().after(o2.getTimeCompleted())) {
                        return -1;
                    }
                    return 0;
                }
            });

            StudentFeeDto item = new StudentFeeDto();
            item.setSemester(semester.getName());
            item.setFromDate(semester.getFromDate());
            item.setToDate(semester.getToDate());

            item.setCreditQuantity(list.stream().mapToLong(StudentCourse::getCreditQuantity).sum());
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            Double totalFee = list.stream().mapToDouble(StudentCourse::getPriceBasic).sum();
            Double completedFee = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
            item.setTotalFee(formatter.format(totalFee) + " VNĐ");
            item.setFeeCompleted(formatter.format(completedFee) + " VNĐ");
            item.setFeeDebt(formatter.format(totalFee - completedFee) + " VNĐ");
            if (tuitionFees.size() > 0) {
                item.setTimeCompleted(tuitionFees.get(0).getTimeCompleted());
            }
            item.setFeeStatus(totalFee.equals(completedFee) ? "Hoàn Thành" : "Chưa Hoàn Thành");
            StringBuilder courseRegister = new StringBuilder("");
            list.forEach(studentCourse -> {
                courseRegister.append(studentCourse.getOpenCourse().getCourse().getCode()).append("(").append(studentCourse.getCreditQuantity()).append("),");
            });
            String str = courseRegister.toString();
            item.setCourseRegister(str.substring(0, str.length()-1));
            result.add(item);
        }
        return result;
    }

}
