package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.AppConstant;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.course.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Course APIs")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "Search course")
    @PostMapping(value = "/public/course/search")
    public ResponseEntity<?> findAllCourse(@RequestParam(value = "page", defaultValue = AppConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                             @RequestParam(value = "size", defaultValue = AppConstant.PAGE_SIZE_DEFAULT) Integer size,
                                             @RequestParam(value = "sort_by", defaultValue = "CREATED") String sortBy,
                                             @RequestParam(value = "sort_direction", defaultValue = "DESC") Sort.Direction sortDirection
                                             ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllCourseService()
                        .execute()));
    }
}
