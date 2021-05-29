package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.dto.user.UserStudentDto;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllUserStudentServiceImpl extends AbstractBaseService<IFindAllUserStudentService.Input, List<UserStudentDto>>
        implements IFindAllUserStudentService<IFindAllUserStudentService.Input, List<UserStudentDto>> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserStudentDto> doing(Input input) {
        return userMapper.toUserStudentDtoList(userRepository.findAllByUserType(EUserType.STUDENT, input.getPageable()));
    }

}
