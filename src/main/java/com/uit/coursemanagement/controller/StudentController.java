package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.data.UserPrincipal;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.student.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "Fee Tuition Student Detail", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/student/fee/token")
    public ResponseEntity<?> findFeeUserStudent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(studentService.getFindAllFeeStudentService().execute(userPrincipal.getId())));
    }

}
