package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.service.semester.IAddSemesterService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import com.uit.coursemanagement.service.semester.ISemesterService;
import com.uit.coursemanagement.service.user.IUserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class SemesterServiceImpl implements ISemesterService {

    @Autowired
    private IFindAllSemesterService findAllSemesterService;

    @Autowired
    private IAddSemesterService addSemesterService;
}
