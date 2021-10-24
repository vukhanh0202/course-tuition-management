package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IUpdateStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateStudentServiceImpl extends AbstractBaseService<UpdateStudentRequest, Boolean>
        implements IUpdateStudentService {

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;

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
