package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.AppConstant;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.service.semester.ISemesterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Semester APIs")
public class SemesterController {

    @Autowired
    private ISemesterService semesterService;

    @ApiOperation(value = "Search semester" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/semester/search")
    public ResponseEntity<?> findAllSemester(@RequestParam(value = "status") EStatus status) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getFindAllSemesterService().execute(status)));
    }

    @ApiOperation(value = "Add new semester" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/semester/add-new-semester")
    public ResponseEntity<?> addNewSemester(@RequestBody AddSemesterRequest addSemesterRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getAddSemesterService()
                        .execute(addSemesterRequest)));
    }
}
