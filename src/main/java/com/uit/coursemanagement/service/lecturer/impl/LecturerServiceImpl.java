package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.service.lecturer.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class LecturerServiceImpl implements ILecturerService {

    private final IAllUserLecturerService allUserLecturerService;

    private final IFindAllUserLecturerService findAllUserLecturerService;

    private final IFindDetailUserLecturerService findDetailUserLecturerService;

    private final IFindCourseRegisterLecturerBySemesterService findCourseRegisterLecturerBySemesterService;

    private final ICreateLecturerService createLecturerService;

    private final IUpdateLecturerService updateLecturerService;

    public LecturerServiceImpl(IAllUserLecturerService allUserLecturerService, IFindAllUserLecturerService findAllUserLecturerService, IFindDetailUserLecturerService findDetailUserLecturerService, IFindCourseRegisterLecturerBySemesterService findCourseRegisterLecturerBySemesterService, ICreateLecturerService createLecturerService, IUpdateLecturerService updateLecturerService) {
        this.allUserLecturerService = allUserLecturerService;
        this.findAllUserLecturerService = findAllUserLecturerService;
        this.findDetailUserLecturerService = findDetailUserLecturerService;
        this.findCourseRegisterLecturerBySemesterService = findCourseRegisterLecturerBySemesterService;
        this.createLecturerService = createLecturerService;
        this.updateLecturerService = updateLecturerService;
    }
}
