package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.repository.user.RoleRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.ICreateStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStudentServiceImpl extends AbstractBaseService<CreateStudentRequest, Boolean>
        implements ICreateStudentService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    @Override
    public void preExecute(CreateStudentRequest updateStudentRequest) {
        User user = userRepository.findByUsernameOrStudentCodeOrEmail(updateStudentRequest.getUsername(),
                updateStudentRequest.getCode(), updateStudentRequest.getEmail()).orElse(null);
        if (user != null) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.EXIST));
        }
    }

    @Override
    public Boolean doing(CreateStudentRequest createStudentRequest) {
        String pwdBcrypt = BCrypt.hashpw(createStudentRequest.getPassword(), BCrypt.gensalt(10));
        createStudentRequest.setPassword(pwdBcrypt);
        User user = userMapper.toStudentUser(createStudentRequest);
        user.setRole(roleRepository.findById(ERoleType.USER).get());
        userRepository.save(user);
        return true;
    }

}
