package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import com.uit.coursemanagement.service.student.IFindDetailStudentService;
import com.uit.coursemanagement.service.student.IStudentService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IFindAllUserStudentService findAllUserStudentService;

    @Autowired
    private ICountAllUserStudentService countAllUserStudentService;

    @Autowired
    private IFindDetailStudentService findDetailStudentService;

}
