package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.service.user.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "User APIs")
public class UserController {

    @Autowired
    private IUserService userService;

}
