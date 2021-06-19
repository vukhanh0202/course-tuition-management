package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.data.UserPrincipal;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;
import com.uit.coursemanagement.service.student.IFindCourseRegisterStudentBySemesterService;
import com.uit.coursemanagement.service.student.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "Student APIs")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation(value = "Search student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/search")
    public ResponseEntity<?> findAllUserStudent(@RequestParam(value = "full_name", defaultValue = "") String fullName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentService.getFindAllUserStudentService().execute(fullName));
    }

    @ApiOperation(value = "Student Detail", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/{id}")
    public ResponseEntity<?> findDetailUserStudent(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindDetailStudentService().execute(id)));
    }

    @ApiOperation(value = "Student Course Register By Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/course/{id}")
    public ResponseEntity<?> findCourseRegisterByStudentAndSemester(@PathVariable("id") Long id,
                                                                    @RequestParam(value = "semester_id") Long semesterId) {
        IFindCourseRegisterStudentBySemesterService.Input input = new IFindCourseRegisterStudentBySemesterService.Input(id, semesterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindCourseRegisterStudentBySemesterService().execute(input)));
    }

    @ApiOperation(value = "Fee Tuition Student Detail", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/fee/token")
    public ResponseEntity<?> findFeeUserStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindAllFeeStudentService().execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "All Fee Tuition Student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/total-fee/token")
    public ResponseEntity<?> findTotalFeeUserStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindTotalFeeStudentService().execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Timetable Student", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/timetable/token")
    public ResponseEntity<?> findTimetableStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindTimetableStudentService().execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Fee Tuition Student Detail", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/student/fee/payment/token")
    public ResponseEntity<?> paymentFeeUserStudent(@RequestBody PaymentFeeRequest paymentFeeRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        paymentFeeRequest.setStudentId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getPaymentFeeStudentService().execute(paymentFeeRequest)));
    }

    @ApiOperation(value = "Update information student", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/student/update")
    public ResponseEntity<?> updateInformationStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updateStudentRequest.setId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getUpdateStudentService().execute(updateStudentRequest)));
    }

    @ApiOperation(value = "Create student", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/student/create")
    public ResponseEntity<?> createUserStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getCreateStudentService().execute(createStudentRequest)));
    }

}
