package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.service.semester.*;
import com.uit.coursemanagement.service.user.IUserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class SemesterServiceImpl implements ISemesterService {

    private final IFindAllSemesterService findAllSemesterService;

    private final IAddSemesterService addSemesterService;

    private final IAllSemesterService allSemesterService;

    private final IUpdateSemesterService updateSemesterService;

    private final IDeleteSemesterService deleteSemesterService;

    public SemesterServiceImpl(IFindAllSemesterService findAllSemesterService, IAddSemesterService addSemesterService, IAllSemesterService allSemesterService, IUpdateSemesterService updateSemesterService, IDeleteSemesterService deleteSemesterService) {
        this.findAllSemesterService = findAllSemesterService;
        this.addSemesterService = addSemesterService;
        this.allSemesterService = allSemesterService;
        this.updateSemesterService = updateSemesterService;
        this.deleteSemesterService = deleteSemesterService;
    }
}
