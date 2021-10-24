package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.dto.response.ApiResponse;
import com.uit.coursemanagement.service.DashBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "DashBoard APIs")
@RequiredArgsConstructor
public class DashBoardController {

    private final DashBoardService dashBoardService;

    @ApiOperation(value = "Get info dashboard", authorizations = {@Authorization(value = "JWT")})
    @GetMapping(value = "/dashboard")
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(dashBoardService.getInfoDashboard()));
    }
}
