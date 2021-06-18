package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.dto.shift.ShiftDto;
import com.uit.coursemanagement.service.tuition.IConfirmTuitionService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionPendingStudentService;
import com.uit.coursemanagement.service.tuition.ITuitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@Api(value = "Tuition APIs")
public class TuitionController {

    @Autowired
    private ITuitionService tuitionService;

    @ApiOperation(value = "Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/tuition/pending/{semester_id}")
    public ResponseEntity<?> findTuitionPendingBySemesterId(@PathVariable("semester_id") Long semester_id,
                                                            @RequestParam(value = "full_name", defaultValue = "") String fullName) {
        IFindAllTuitionPendingStudentService.Input input = new IFindAllTuitionPendingStudentService.Input(semester_id,fullName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getFindAllTuitionPendingStudentService().execute(input)));
    }

    @ApiOperation(value = "Confirm Tuition pending", authorizations = {@Authorization(value = "JWT")})
    @PostMapping(value = "/tuition/confirm/{id}")
    public ResponseEntity<?> confirmTuition(@PathVariable("id") Long tuitionId,
                                            @RequestParam(value = "confirm") Boolean confirm) {
        IConfirmTuitionService.Input input = new IConfirmTuitionService.Input(tuitionId,confirm);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(tuitionService.getConfirmTuitionService().execute(input)));
    }
}
