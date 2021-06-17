package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.service.student.*;
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

    private final IPaymentFeeStudentService paymentFeeStudentService;

    private final IFindTotalFeeStudentService findTotalFeeStudentService;

    private final IFindTimetableStudentService findTimetableStudentService;

    private final IUpdateStudentService updateStudentService;

    public StudentServiceImpl(IFindAllUserStudentService findAllUserStudentService, IFindDetailStudentService findDetailStudentService, IFindAllFeeStudentService findAllFeeStudentService, IPaymentFeeStudentService paymentFeeStudentService, IFindTotalFeeStudentService findTotalFeeStudentService, IFindTimetableStudentService findTimetableStudentService, IUpdateStudentService updateStudentService) {
        this.findAllUserStudentService = findAllUserStudentService;
        this.findDetailStudentService = findDetailStudentService;
        this.findAllFeeStudentService = findAllFeeStudentService;
        this.paymentFeeStudentService = paymentFeeStudentService;
        this.findTotalFeeStudentService = findTotalFeeStudentService;
        this.findTimetableStudentService = findTimetableStudentService;
        this.updateStudentService = updateStudentService;
    }
}
