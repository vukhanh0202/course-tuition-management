package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.lecturer.Lecturer;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.payload.lecturer.CreateLecturerRequest;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.repository.user.RoleRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.ICreateLecturerService;
import com.uit.coursemanagement.service.student.ICreateStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateLecturerServiceImpl extends AbstractBaseService<CreateLecturerRequest, Boolean>
        implements ICreateLecturerService<CreateLecturerRequest, Boolean> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void preExecute(CreateLecturerRequest createLecturerRequest) {
        User user = userRepository.findByUsernameOrEmail(createLecturerRequest.getUsername(), createLecturerRequest.getEmail()).orElse(null);
        if (user != null) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.EXIST));
        }
    }

    @Override
    public Boolean doing(CreateLecturerRequest createLecturerRequest) {
        String pwdBcrypt = BCrypt.hashpw(createLecturerRequest.getPassword(), BCrypt.gensalt(10));
        createLecturerRequest.setPassword(pwdBcrypt);
        User user = userMapper.toLecturerUser(createLecturerRequest);
        user.setRole(roleRepository.findById(ERoleType.ADMIN).get());
        user.setUserType(EUserType.LECTURER);
        user.setLecturer(new Lecturer());
        userRepository.save(user);
        return true;
    }

}
