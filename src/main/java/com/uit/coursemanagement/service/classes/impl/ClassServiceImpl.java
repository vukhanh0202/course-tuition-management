package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.service.classes.*;
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

    private final IFindAllClassService findAllClassService;

    private final IAddClassService addClassService;

    private final IUpdateClassService updateClassService;

    private final IDeleteClassService deleteClassService;

    private final IFindClassDetailService findClassDetailService;

    private final IAllClassService allClassService;

    public ClassServiceImpl(IFindAllClassService findAllClassService, IAddClassService addClassService, IUpdateClassService updateClassService, IDeleteClassService deleteClassService, IFindClassDetailService findClassDetailService, IAllClassService allClassService) {
        this.findAllClassService = findAllClassService;
        this.addClassService = addClassService;
        this.updateClassService = updateClassService;
        this.deleteClassService = deleteClassService;
        this.findClassDetailService = findClassDetailService;
        this.allClassService = allClassService;
    }
}
