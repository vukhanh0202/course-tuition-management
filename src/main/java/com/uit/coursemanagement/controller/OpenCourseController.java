package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.service.course.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Open Course APIs")
public class OpenCourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "Open course search" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/open-course/search")
    public ResponseEntity<?> openCourseSearch() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllOpenCourseService()
                        .execute()));
    }


    @ApiOperation(value = "Open course current search" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/open-course/current/search")
    public ResponseEntity<?> openCourseCurrentSearch() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllOpenCourseCurrentService()
                        .execute()));
    }

    @ApiOperation(value = "Open course" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/open-course")
    public ResponseEntity<?> openCourse(@RequestBody OpenCourseRequest openCourseRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getOpenCourseService()
                        .execute(openCourseRequest)));
    }
}
