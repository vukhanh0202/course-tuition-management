package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.service.lecturer.IAllUserLecturerService;
import com.uit.coursemanagement.service.lecturer.IFindAllUserLecturerService;
import com.uit.coursemanagement.service.lecturer.IFindDetailUserLecturerService;
import com.uit.coursemanagement.service.lecturer.ILecturerService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class LecturerServiceImpl implements ILecturerService {

    private final IAllUserLecturerService allUserLecturerService;

    private final IFindAllUserLecturerService findAllUserLecturerService;

    private final IFindDetailUserLecturerService findDetailUserLecturerService;

    public LecturerServiceImpl(IAllUserLecturerService allUserLecturerService, IFindAllUserLecturerService findAllUserLecturerService, IFindDetailUserLecturerService findDetailUserLecturerService) {
        this.allUserLecturerService = allUserLecturerService;
        this.findAllUserLecturerService = findAllUserLecturerService;
        this.findDetailUserLecturerService = findDetailUserLecturerService;
    }
}
