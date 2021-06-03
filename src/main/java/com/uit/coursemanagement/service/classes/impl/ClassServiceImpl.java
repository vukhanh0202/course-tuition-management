package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.classes.IClassService;
import com.uit.coursemanagement.service.classes.IFindAllClassService;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import com.uit.coursemanagement.service.semester.ISemesterService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class ClassServiceImpl implements IClassService {

    @Autowired
    private IFindAllClassService findAllClassService;

    @Autowired
    private IAddClassService addClassService;
}