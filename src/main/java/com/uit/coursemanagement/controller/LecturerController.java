package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.lecturer.CreateLecturerRequest;
import com.uit.coursemanagement.payload.lecturer.UpdateLecturerRequest;
import com.uit.coursemanagement.service.lecturer.IFindCourseRegisterLecturerBySemesterService;
import com.uit.coursemanagement.service.lecturer.ILecturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Api(value = "Lecturer APIs")
public class LecturerController {

    @Autowired
    private ILecturerService lecturerService;

    @ApiOperation(value = "All Lecturer", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/lecturer/all")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> allLecturer() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getAllUserLecturerService().execute()));
    }

    @ApiOperation(value = "Search lecturer", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/lecturer/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findAllUserLecturer(@RequestParam(value = "full_name", defaultValue = "") String fullName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getFindAllUserLecturerService().execute(fullName)));
    }

    @ApiOperation(value = "Detail lecturer", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/lecturer/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findDetailUserLecturer(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getFindDetailUserLecturerService().execute(id)));
    }

    @ApiOperation(value = "Lecturer Course Register By Semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/lecturer/course/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findCourseRegisterByLecturerAndSemester(@PathVariable("id") Long id,
                                                                    @RequestParam(value = "semester_id") Long semesterId) {
        IFindCourseRegisterLecturerBySemesterService.Input input = new IFindCourseRegisterLecturerBySemesterService.Input(id, semesterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getFindCourseRegisterLecturerBySemesterService().execute(input)));
    }

    @ApiOperation(value = "Create lecturer", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/lecturer/create")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createUserStudent(@RequestBody CreateLecturerRequest createStudentRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getCreateLecturerService().execute(createStudentRequest)));
    }

    @ApiOperation(value = "Update lecturer", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/lecturer/update/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> createUserStudent(@PathVariable("id") Long id,
                                               @RequestBody UpdateLecturerRequest updateLecturerRequest) {
        updateLecturerRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(lecturerService.getUpdateLecturerService().execute(updateLecturerRequest)));
    }
}
