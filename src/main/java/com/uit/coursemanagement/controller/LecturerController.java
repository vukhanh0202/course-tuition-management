package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.lecturer.ILecturerService;
import com.uit.coursemanagement.service.student.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(value = "Lecturer APIs")
public class LecturerController {

    @Autowired
    private ILecturerService lecturerService;

    @ApiOperation(value = "All Lecturer", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/lecturer/all")
    public ResponseEntity<?> allCourse() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getAllUserLecturerService().execute()));
    }
}
