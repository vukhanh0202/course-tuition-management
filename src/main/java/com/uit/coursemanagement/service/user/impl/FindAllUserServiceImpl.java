package com.uit.coursemanagement.service.user.impl;

import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.user.UserFullDto;
import com.uit.coursemanagement.mapper.course.CourseMapper;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import com.uit.coursemanagement.service.user.IFindAllUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllUserServiceImpl extends AbstractBaseService<Void, List<UserFullDto>>
        implements IFindAllUserService<Void, List<UserFullDto>> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserFullDto> doing(Void unused) {
        return userMapper.toUserFullDtoList(userRepository.findAll());
    }

}
