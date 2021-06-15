package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.service.student.IFindAllFeeStudentService;
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

    private final IFindAllUserStudentService findAllUserStudentService;

    private final IFindDetailStudentService findDetailStudentService;

    private final IFindAllFeeStudentService findAllFeeStudentService;

    public StudentServiceImpl(IFindAllUserStudentService findAllUserStudentService, IFindDetailStudentService findDetailStudentService, IFindAllFeeStudentService findAllFeeStudentService) {
        this.findAllUserStudentService = findAllUserStudentService;
        this.findDetailStudentService = findDetailStudentService;
        this.findAllFeeStudentService = findAllFeeStudentService;
    }
}
