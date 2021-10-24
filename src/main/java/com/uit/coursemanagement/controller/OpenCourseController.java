package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.data.UserPrincipal;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.RegisterOpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;
import com.uit.coursemanagement.service.course.*;
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
@Api(value = "Open Course APIs")
@RequiredArgsConstructor
public class OpenCourseController {

    private final IFindAllOpenCourseService findAllOpenCourseService;

    private final IFindAllOpenCourseCurrentService findAllOpenCourseCurrentService;

    private final IRegisterOpenCourseService registerOpenCourseService;

    private final IDeleteRegisterOpenCourseService deleteRegisterOpenCourseService;

    private final IFindMyOpenCourseCurrentService findMyOpenCourseCurrentService;

    private final IOpenCourseService openCourseService;

    private final IUpdateOpenCourseService updateOpenCourseService;

    private final IDeleteOpenCourseService deleteOpenCourseService;

    @ApiOperation(value = "Open course search", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/open-course/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> openCourseSearch(@RequestParam(value = "search", defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllOpenCourseService.execute(search)));
    }


    @ApiOperation(value = "Open course current search", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/open-course/current/search")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> openCourseCurrentSearch(/*@RequestParam(value = "search", defaultValue = "") String search*/) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllOpenCourseCurrentService.execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Open course current register", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/open-course/current/register")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> registerOpenCourseCurrentSearch(@RequestBody RegisterOpenCourseRequest registerOpenCourseRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        registerOpenCourseRequest.setUserId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(registerOpenCourseService.execute(registerOpenCourseRequest)));
    }

    @ApiOperation(value = "Open course current delete", authorizations = {@Authorization(value = "JWT")})
    @DeleteMapping(value = "/open-course/current/delete")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> deleteOpenCourseCurrentSearch(@RequestBody RegisterOpenCourseRequest deleteOpenCourseRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteOpenCourseRequest.setUserId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(deleteRegisterOpenCourseService.execute(deleteOpenCourseRequest)));
    }

    @ApiOperation(value = "My Open course current", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/my-open-course/current/token")
    @PreAuthorize("@securityService.hasRole('USER')")
    public ResponseEntity<?> myOpenCourseCurrent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findMyOpenCourseCurrentService.execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Open course", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/open-course")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> openCourse(@RequestBody OpenCourseRequest openCourseRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(openCourseService.execute(openCourseRequest)));
    }

    @ApiOperation(value = "Update Open course", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/open-course/update/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> updateOpenCourse(@PathVariable("id") Long id,
                                              @RequestBody UpdateOpenCourseRequest updateOpenCourseRequest) {
        updateOpenCourseRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(updateOpenCourseService.execute(updateOpenCourseRequest)));
    }

    @ApiOperation(value = "Delete Open course", authorizations = {@Authorization(value = "JWT")})
    @DeleteMapping(value = "/open-course/delete/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> deleteOpenCourse(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(deleteOpenCourseService.execute(id)));
    }
}
