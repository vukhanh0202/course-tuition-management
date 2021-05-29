package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.ICountAllUserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountAllUserStudentServiceImpl extends AbstractBaseService<Void, Long>
        implements ICountAllUserStudentService<Void, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long doing(Void input) {
        return userRepository.countAllByUserType(EUserType.STUDENT);
    }

}
