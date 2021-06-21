package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.payload.lecturer.UpdateLecturerRequest;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.IUpdateLecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateLecturerServiceImpl extends AbstractBaseService<UpdateLecturerRequest, Boolean>
        implements IUpdateLecturerService<UpdateLecturerRequest, Boolean> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void preExecute(UpdateLecturerRequest updateStudentRequest) {
        User user = userRepository.findById(updateStudentRequest.getId()).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.LECTURER)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
    }

    @Override
    public Boolean doing(UpdateLecturerRequest updateLecturerRequest) {
        User user = userRepository.findById(updateLecturerRequest.getId()).get();
        userMapper.updateLecturer(updateLecturerRequest, user);
        userRepository.save(user);
        return true;
    }

}
