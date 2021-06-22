package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.AppConstant;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import com.uit.coursemanagement.service.semester.ISemesterService;
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
@Api(value = "Semester APIs")
public class SemesterController {

    @Autowired
    private ISemesterService semesterService;

    @ApiOperation(value = "Search semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/semester/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findAllSemester(@RequestParam(value = "search", defaultValue = "") String search,
                                             @RequestParam(value = "status") EStatus status) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getFindAllSemesterService().execute(new IFindAllSemesterService.Input(status, search))));
    }

    @ApiOperation(value = "All semester", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/semester/all")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> allSemester() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getAllSemesterService().execute()));
    }

    @ApiOperation(value = "Add new semester", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/semester/add-new-semester")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> addNewSemester(@RequestBody AddSemesterRequest addSemesterRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getAddSemesterService()
                        .execute(addSemesterRequest)));
    }

    @ApiOperation(value = "Update semester", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/semester/update/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> updateSemester(@PathVariable("id") Long id,
                                            @RequestBody UpdateSemesterRequest updateSemesterRequest) {
        updateSemesterRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getUpdateSemesterService()
                        .execute(updateSemesterRequest)));
    }

    @ApiOperation(value = "Delete semester", authorizations = {@Authorization(value = "JWT")})
    @DeleteMapping(value = "/semester/delete/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> deleteSemester(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(semesterService.getDeleteSemesterService()
                        .execute(id)));
    }
}
