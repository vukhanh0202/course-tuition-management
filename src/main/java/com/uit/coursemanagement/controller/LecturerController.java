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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation(value = "Search lecturer", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/lecturer/search")
    public ResponseEntity<?> findAllUserLecturer(@RequestParam(value = "full_name", defaultValue = "") String fullName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getFindAllUserLecturerService().execute(fullName)));
    }

    @ApiOperation(value = "Detail lecturer", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/lecturer/{id}")
    public ResponseEntity<?> findDetailUserLecturer(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getFindDetailUserLecturerService().execute(id)));
    }
}
