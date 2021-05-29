package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.AppConstant;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "User APIs")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Search student", authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/student/search")
    public ResponseEntity<?> findAllUserStudent(@RequestParam(value = "page", defaultValue = AppConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                           @RequestParam(value = "size", defaultValue = AppConstant.PAGE_SIZE_DEFAULT) Integer size,
                                           @RequestParam(value = "sort_by", defaultValue = "CREATED") String sortBy,
                                           @RequestParam(value = "sort_direction", defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(userService.getFindAllUserStudentService()
                        .execute()));
    }
}
