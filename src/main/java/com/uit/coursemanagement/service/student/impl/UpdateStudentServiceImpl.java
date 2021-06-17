package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IPaymentFeeStudentService;
import com.uit.coursemanagement.service.student.IUpdateStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UpdateStudentServiceImpl extends AbstractBaseService<UpdateStudentRequest, Boolean>
        implements IUpdateStudentService<UpdateStudentRequest, Boolean> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void preExecute(UpdateStudentRequest updateStudentRequest) {
        User user = userRepository.findById(updateStudentRequest.getId()).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
    }

    @Override
    public Boolean doing(UpdateStudentRequest updateStudentRequest) {
        User user = userRepository.findById(updateStudentRequest.getId()).get();
        studentMapper.updateStudent(updateStudentRequest, user);
        userRepository.save(user);
        return true;
    }

}
