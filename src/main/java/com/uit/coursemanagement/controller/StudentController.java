package com.uit.coursemanagement.controller;

import com.uit.coursemanagement.constant.AppConstant;
import com.uit.coursemanagement.dto.response.PaginationResponse;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import com.uit.coursemanagement.service.student.IStudentService;
import com.uit.coursemanagement.service.user.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Student APIs")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation(value = "Search student", authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/student/search")
    public ResponseEntity<?> findAllUserStudent(@RequestParam(value = "page", defaultValue = AppConstant.PAGE_NUMBER_DEFAULT) Integer page,
                                           @RequestParam(value = "size", defaultValue = AppConstant.PAGE_SIZE_DEFAULT) Integer size,
                                           @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
                                           @RequestParam(value = "sort_direction", defaultValue = "DESC") Sort.Direction sortDirection) {
        IFindAllUserStudentService.Input input = new IFindAllUserStudentService.Input(page, size);
        input.createPageable(sortDirection, sortBy);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new PaginationResponse(
                        Integer.parseInt(studentService.getCountAllUserStudentService().execute().toString())
                        , size
                        , page
                        , studentService.getFindAllUserStudentService().execute(input)));
    }

    @ApiOperation(value = "Student Detail", authorizations = { @Authorization(value="JWT") })
    @PostMapping(value = "/student/{id}")
    public ResponseEntity<?> findAllUserStudent(@PathVariable("id") Long id) {
        return null;
    }
}
