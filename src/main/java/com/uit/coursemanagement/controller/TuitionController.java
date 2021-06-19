package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.dto.shift.ShiftDto;
import com.uit.coursemanagement.service.student.IFindCourseRegisterStudentBySemesterService;
import com.uit.coursemanagement.service.student.IStudentService;
import com.uit.coursemanagement.service.tuition.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@Api(value = "Tuition APIs")
public class TuitionController {

    @Autowired
    private ITuitionService tuitionService;

    @Autowired
    private IStudentService studentService;

    @ApiOperation(value = "Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/pending/{semester_id}")
    public ResponseEntity<?> findTuitionPendingBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                            @RequestParam(value = "full_name", defaultValue = "") String fullName) {
        IFindAllTuitionPendingStudentService.Input input = new IFindAllTuitionPendingStudentService.Input(semesterId,fullName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getFindAllTuitionPendingStudentService().execute(input)));
    }

    @ApiOperation(value = "Confirm Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/tuition/confirm/{id}")
    public ResponseEntity<?> confirmTuition(@PathVariable("id") Long tuitionId,
                                            @RequestParam(value = "confirm") Boolean confirm) {
        IConfirmTuitionService.Input input = new IConfirmTuitionService.Input(tuitionId,confirm);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getConfirmTuitionService().execute(input)));
    }

    @ApiOperation(value = "Tuition All Student In Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/{semester_id}")
    public ResponseEntity<?> findTuitionBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                     @RequestParam(value = "full_name", defaultValue = "") String fullName) {
        IFindAllTuitionInSemesterStudentService.Input input = new IFindAllTuitionInSemesterStudentService.Input(semesterId,fullName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getFindAllTuitionInSemesterStudentService().execute(input)));
    }

    @ApiOperation(value = "Tuition Detail Student In Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/detail/{student_id}")
    public ResponseEntity<?> findTuitionDetailStudentBySemesterId(@PathVariable("student_id") Long studentId,
                                                     @RequestParam(value = "semester_id") Long semesterId) {
        IFindCourseRegisterStudentBySemesterService.Input input = new IFindCourseRegisterStudentBySemesterService.Input(studentId, semesterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindCourseRegisterStudentBySemesterService().execute(input)));
    }

    @ApiOperation(value = "Confirm tuition student in semester", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/tuition/payment/{semester_id}")
    public ResponseEntity<?> confirmStudentTuitionBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                     @RequestParam(value = "student_id") Long studentId) {
        IConfirmStudentTuitionInSemesterStudentService.Input input = new IConfirmStudentTuitionInSemesterStudentService.Input(semesterId,studentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getConfirmStudentTuitionInSemesterStudentService().execute(input)));
    }

}
