package com.uit.coursemanagement.service.user.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.dto.user.UserStudentDto;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.user.IFindAllUserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllUserStudentServiceImpl extends AbstractBaseService<Void, List<UserStudentDto>>
        implements IFindAllUserStudentService<Void, List<UserStudentDto>> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserStudentDto> doing(Void unused) {
        return userMapper.toUserStudentDtoList(userRepository.findAllByUserType(EUserType.STUDENT));
    }

}
