package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateCourseRequest;
import com.uit.coursemanagement.service.course.*;
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
@Api(value = "Course APIs")
@RequiredArgsConstructor
public class CourseController {

    private final IFindAllCourseService findAllCourseService;

    private final IAllCourseService allCourseService;

    private final IAddNewCourseService addNewCourseService;

    private final IUpdateCourseService updateCourseService;

    private final IDeleteCourseService deleteCourseService;

    @ApiOperation(value = "Search course", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/course/search")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> findAllCourse(@RequestParam(value = "course_name", defaultValue = "") String courseName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(findAllCourseService.execute(courseName)));
    }

    @ApiOperation(value = "All course")
    @GetMapping(value = "/course/all")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> allCourse() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(allCourseService.execute()));
    }

    @ApiOperation(value = "Add new course" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/add-new-course")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> addNewCourse(@RequestBody AddNewCourseRequest addNewCourseRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(addNewCourseService.execute(addNewCourseRequest)));
    }

    @ApiOperation(value = "Update course" , authorizations = { @Authorization(value="JWT") })
    @PutMapping(value = "/course/update/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> updateCourse(@PathVariable("id") Long id,
                                          @RequestBody UpdateCourseRequest updateClassRequest) {
        updateClassRequest.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(updateCourseService.execute(updateClassRequest)));
    }

    @ApiOperation(value = "Delete course" , authorizations = { @Authorization(value="JWT") })
    @DeleteMapping(value = "/course/delete/{id}")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(deleteCourseService.execute(id)));
    }

}
