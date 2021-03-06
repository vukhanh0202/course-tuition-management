package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.student.IFindCourseRegisterStudentBySemesterService;
import com.uit.coursemanagement.service.tuition.IConfirmStudentTuitionInSemesterStudentService;
import com.uit.coursemanagement.service.tuition.IConfirmTuitionService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionInSemesterStudentService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionPendingStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Tuition APIs")
@RequiredArgsConstructor
public class TuitionController {

    private final IFindAllTuitionPendingStudentService findAllTuitionPendingStudentService;

    private final IConfirmTuitionService confirmTuitionService;

    private final IFindAllTuitionInSemesterStudentService findAllTuitionInSemesterStudentService;

    private final IFindCourseRegisterStudentBySemesterService findCourseRegisterStudentBySemesterService;

    private final IConfirmStudentTuitionInSemesterStudentService confirmStudentTuitionInSemesterStudentService;

    @ApiOperation(value = "Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/pending/{semester_id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findTuitionPendingBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                            @RequestParam(value = "full_name", defaultValue = "") String fullName) {
        IFindAllTuitionPendingStudentService.Input input = new IFindAllTuitionPendingStudentService.Input(semesterId,fullName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllTuitionPendingStudentService.execute(input)));
    }

    @ApiOperation(value = "Confirm Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/tuition/confirm/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> confirmTuition(@PathVariable("id") Long tuitionId,
                                            @RequestParam(value = "confirm") Boolean confirm) {
        IConfirmTuitionService.Input input = new IConfirmTuitionService.Input(tuitionId,confirm);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(confirmTuitionService.execute(input)));
    }

    @ApiOperation(value = "Tuition All Student In Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/{semester_id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findTuitionBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                     @RequestParam(value = "full_name", defaultValue = "") String fullName) {
        IFindAllTuitionInSemesterStudentService.Input input = new IFindAllTuitionInSemesterStudentService.Input(semesterId,fullName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllTuitionInSemesterStudentService.execute(input)));
    }

    @ApiOperation(value = "Tuition Detail Student In Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/detail/{student_id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findTuitionDetailStudentBySemesterId(@PathVariable("student_id") Long studentId,
                                                     @RequestParam(value = "semester_id") Long semesterId) {
        IFindCourseRegisterStudentBySemesterService.Input input = new IFindCourseRegisterStudentBySemesterService.Input(studentId, semesterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findCourseRegisterStudentBySemesterService.execute(input)));
    }

    @ApiOperation(value = "Confirm tuition student in semester", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/tuition/payment/{semester_id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> confirmStudentTuitionBySemesterId(@PathVariable("semester_id") Long semesterId,
                                                     @RequestParam(value = "student_id") Long studentId) {
        IConfirmStudentTuitionInSemesterStudentService.Input input = new IConfirmStudentTuitionInSemesterStudentService.Input(semesterId,studentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(confirmStudentTuitionInSemesterStudentService.execute(input)));
    }
}
