package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.service.classes.IClassService;
import com.uit.coursemanagement.service.course.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Class APIs")
public class ClassesController {

    @Autowired
    private IClassService classService;

    @ApiOperation(value = "Search class", authorizations = { @Authorization(value="JWT") })
    @GetMapping(value = "/class/search")
    public ResponseEntity<?> findAllClass() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(classService.getFindAllClassService().execute()));
    }

    @ApiOperation(value = "Add new class" , authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/add-new-class")
    public ResponseEntity<?> addNewClass(@RequestBody AddClassRequest addClassRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(classService.getAddClassService()
                        .execute(addClassRequest)));
    }
}
