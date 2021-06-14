package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.data.UserPrincipal;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.RegisterOpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;
import com.uit.coursemanagement.service.course.ICourseService;
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
@Api(value = "Open Course APIs")
public class OpenCourseController {

    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "Open course search" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/open-course/search")
    public ResponseEntity<?> openCourseSearch(@RequestParam(value = "search", defaultValue = "") String search) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllOpenCourseService()
                        .execute(search)));
    }


    @ApiOperation(value = "Open course current search" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/open-course/current/search")
    public ResponseEntity<?> openCourseCurrentSearch(/*@RequestParam(value = "search", defaultValue = "") String search*/) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindAllOpenCourseCurrentService()
                        .execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Open course current register" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/open-course/current/register")
    public ResponseEntity<?> registerOpenCourseCurrentSearch(@RequestBody RegisterOpenCourseRequest registerOpenCourseRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        registerOpenCourseRequest.setUserId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getRegisterOpenCourseService()
                        .execute(registerOpenCourseRequest)));
    }

    @ApiOperation(value = "Open course current delete" , authorizations = { @Authorization(value="JWT") })
    @DeleteMapping(value = "/open-course/current/delete")
    public ResponseEntity<?> deleteOpenCourseCurrentSearch(@RequestBody RegisterOpenCourseRequest deleteOpenCourseRequest) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        deleteOpenCourseRequest.setUserId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getDeleteRegisterOpenCourseService()
                        .execute(deleteOpenCourseRequest)));
    }

    @ApiOperation(value = "My Open course current" , authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/my-open-course/current/token")
    public ResponseEntity<?> myOpenCourseCurrent() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getFindMyOpenCourseCurrentService()
                        .execute(userPrincipal.getId())));
    }

    @ApiOperation(value = "Open course" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/open-course")
    public ResponseEntity<?> openCourse(@RequestBody OpenCourseRequest openCourseRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getOpenCourseService()
                        .execute(openCourseRequest)));
    }

    @ApiOperation(value = "Update Open course" , authorizations = { @Authorization(value="JWT") })
    @PutMapping(value = "/open-course/update/{id}")
    public ResponseEntity<?> updateOpenCourse(@PathVariable("id") Long id,
                                              @RequestBody UpdateOpenCourseRequest updateOpenCourseRequest) {
        updateOpenCourseRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getUpdateOpenCourseService()
                        .execute(updateOpenCourseRequest)));
    }

    @ApiOperation(value = "Delete Open course" , authorizations = { @Authorization(value="JWT") })
    @DeleteMapping(value = "/open-course/delete/{id}")
    public ResponseEntity<?> deleteOpenCourse(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(courseService.getDeleteOpenCourseService()
                        .execute(id)));
    }
}
