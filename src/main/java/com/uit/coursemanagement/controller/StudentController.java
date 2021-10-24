package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.data.UserPrincipal;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;
import com.uit.coursemanagement.service.student.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Student APIs")
@RequiredArgsConstructor
public class StudentController {

    private final IFindAllUserStudentService findAllUserStudentService;

    private final IFindDetailStudentService findDetailStudentService;

    private final IFindCourseRegisterStudentBySemesterService findCourseRegisterStudentBySemesterService;

    private final IFindAllFeeStudentService findAllFeeStudentService;

    private final IFindTotalFeeStudentService findTotalFeeStudentService;

    private final IFindTimetableStudentService findTimetableStudentService;

    private final IPaymentFeeStudentService paymentFeeStudentService;

    private final IUpdateStudentService updateStudentService;

    private final ICreateStudentService createStudentService;

    @ApiOperation(value = "Search student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findAllUserStudent(@RequestParam(value = "full_name", defaultValue = "") String fullName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findAllUserStudentService.execute(fullName));
    }

    @ApiOperation(value = "Student Detail", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findDetailUserStudent(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findDetailStudentService.execute(id)));
    }

    @ApiOperation(value = "Student Course Register By Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/course/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findCourseRegisterByStudentAndSemester(@PathVariable("id") Long id,
                                                                    @RequestParam(value = "semester_id") Long semesterId) {
        IFindCourseRegisterStudentBySemesterService.Input input = new IFindCourseRegisterStudentBySemesterService.Input(id, semesterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findCourseRegisterStudentBySemesterService.execute(input)));
    }

    @ApiOperation(value = "Fee Tuition Student Detail", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/fee/token")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> findFeeUserStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllFeeStudentService.execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "All Fee Tuition Student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/total-fee/token")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> findTotalFeeUserStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findTotalFeeStudentService.execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Timetable Student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/timetable/token")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> findTimetableStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findTimetableStudentService.execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Fee Tuition Student Detail", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/student/fee/payment/token")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> paymentFeeUserStudent(@RequestBody PaymentFeeRequest paymentFeeRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        paymentFeeRequest.setStudentId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(paymentFeeStudentService.execute(paymentFeeRequest)));
    }

    @ApiOperation(value = "Update information student", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/student/update")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> updateInformationStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updateStudentRequest.setId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(updateStudentService.execute(updateStudentRequest)));
    }

    @ApiOperation(value = "Create student", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/student/create")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createUserStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(createStudentService.execute(createStudentRequest)));
    }

}
