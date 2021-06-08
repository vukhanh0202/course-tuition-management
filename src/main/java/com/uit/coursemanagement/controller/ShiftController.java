package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.dto.shift.ShiftDto;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.service.classes.IClassService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@Api(value = "Shift APIs")
public class ShiftController {


    @ApiOperation(value = "Shift class", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/shift/all")
    public ResponseEntity<?> findAllClass() {
        List<ShiftDto> rs = new ArrayList<>();
        Stream.of(ECalendarShift.values())
                .forEach(eCalendarShift -> {
                    rs.add(new ShiftDto(eCalendarShift.name(), eCalendarShift.getValueString()));
                });
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(rs));
    }
}
