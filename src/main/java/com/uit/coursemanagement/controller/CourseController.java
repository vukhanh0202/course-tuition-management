package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
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
@Api(value = "Course APIs")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "Search course")
    @GetMapping(value = "/public/course/search")
    public ResponseEntity<?> findAllCourse() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllCourseService().execute()));
    }

    @ApiOperation(value = "Add new course" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/add-new-course")
    public ResponseEntity<?> addNewCourse(@RequestBody AddNewCourseRequest addNewCourseRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getAddNewCourseService()
                        .execute(addNewCourseRequest)));
    }
}
