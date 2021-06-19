package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.service.tuition.*;
import com.uit.coursemanagement.service.user.IUserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class TuitionServiceImpl implements ITuitionService {

    @Autowired
    private IFindAllTuitionPendingStudentService findAllTuitionPendingStudentService;

    @Autowired
    private IConfirmTuitionService confirmTuitionService;

    @Autowired
    private IFindAllTuitionInSemesterStudentService findAllTuitionInSemesterStudentService;

    @Autowired
    private IConfirmStudentTuitionInSemesterStudentService confirmStudentTuitionInSemesterStudentService;
}
