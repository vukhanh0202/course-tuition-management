package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import com.uit.coursemanagement.service.classes.*;
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
@Api(value = "Class APIs")
@RequiredArgsConstructor
public class ClassesController {

    private final IFindAllClassService findAllClassService;

    private final IAllClassService allClassService;

    private final IFindClassDetailService findClassDetailService;

    private final IAddClassService addClassService;

    private final IUpdateClassService updateClassService;

    private final IDeleteClassService deleteClassService;

    @ApiOperation(value = "Search class", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/class/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findAllClass(@RequestParam(value = "class_name", defaultValue = "") String className) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllClassService.execute(className)));
    }

    @ApiOperation(value = "All class", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/class/all")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> allClass() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(allClassService.execute()));
    }

    @ApiOperation(value = "Get class detail", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/class/detail/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findClassDetail(@PathVariable("id") Long id,
                                             @RequestParam(value = "semester_id") Long semesterId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findClassDetailService .execute(new IFindClassDetailService.Input(id,semesterId))));
    }

    @ApiOperation(value = "Add new class" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/add-new-class")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> addNewClass(@RequestBody AddClassRequest addClassRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(addClassService.execute(addClassRequest)));
    }

    @ApiOperation(value = "Update class" , authorizations = { @Authorization(value="JWT") })
    @PutMapping(value = "/class/update/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> updateClass(@PathVariable("id") Long id,
                                         @RequestBody UpdateClassRequest updateClassRequest) {
        updateClassRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(updateClassService.execute(updateClassRequest)));
    }

    @ApiOperation(value = "Delete class" , authorizations = { @Authorization(value="JWT") })
    @DeleteMapping(value = "/class/delete/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> deleteClass(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(deleteClassService.execute(id)));
    }
}
