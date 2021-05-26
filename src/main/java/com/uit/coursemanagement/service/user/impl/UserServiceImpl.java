package com.uit.coursemanagement.service.user.impl;

import com.uit.coursemanagement.service.course.ICourseService;
import com.uit.coursemanagement.service.course.IFindAllCourseService;
import com.uit.coursemanagement.service.user.IFindAllUserService;
import com.uit.coursemanagement.service.user.IUserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IFindAllUserService findAllUserService;

}
