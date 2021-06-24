package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.tuition.IConfirmStudentTuitionInSemesterStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ConfirmTuitionInSemesterServiceImpl extends AbstractBaseService<IConfirmStudentTuitionInSemesterStudentService.Input, Boolean>
        implements IConfirmStudentTuitionInSemesterStudentService<IConfirmStudentTuitionInSemesterStudentService.Input, Boolean> {

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Boolean doing(Input input) {
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(input.getStudentId(), input.getSemesterId());
        List<TuitionFee> tuitionFees = tuitionFeeRepository
                .findAllByStudentIdAndSemesterIdAndStatus(input.getStudentId(), input.getSemesterId(), EStatus.COMPLETED);
        AtomicReference<Double> totalFee = new AtomicReference<>(0d);
        studentCourses.stream().forEach(item->{
            totalFee.set(totalFee.get() + item.getPriceBasic() * item.getCreditQuantity());
        });
        double totalFeeCompleted = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
        if (totalFeeCompleted >= totalFee.get()) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.Tuition.IS_COMPLETED));
        }

        TuitionFee tuitionFee = new TuitionFee();
        Student student = userRepository.findById(input.getStudentId()).get().getStudent();
        Semester semester = semesterRepository.findById(input.getSemesterId()).get();
        tuitionFee.setStudent(student);
        tuitionFee.setSemester(semester);
        tuitionFee.setTotalFee(totalFee.get() - totalFeeCompleted);
        tuitionFee.setTimeCompleted(new Date());
        tuitionFee.setStatus(EStatus.COMPLETED);
        tuitionFeeRepository.save(tuitionFee);
        return true;
    }
}
