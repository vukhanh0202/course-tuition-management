package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.service.course.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class CourseServiceImpl implements ICourseService {

    private final IFindAllCourseService findAllCourseService;

    private final IAddNewCourseService addNewCourseService;

    private final IOpenCourseService openCourseService;

    private final IFindAllOpenCourseService findAllOpenCourseService;

    private final IUpdateCourseService updateCourseService;

    private final IDeleteCourseService deleteCourseService;

    private final IFindAllOpenCourseCurrentService findAllOpenCourseCurrentService;

    private final IAllCourseService allCourseService;

    public CourseServiceImpl(IFindAllCourseService findAllCourseService, IAddNewCourseService addNewCourseService, IOpenCourseService openCourseService, IFindAllOpenCourseService findAllOpenCourseService, IUpdateCourseService updateCourseService, IDeleteCourseService deleteCourseService, IFindAllOpenCourseCurrentService findAllOpenCourseCurrentService, IAllCourseService allCourseService) {
        this.findAllCourseService = findAllCourseService;
        this.addNewCourseService = addNewCourseService;
        this.openCourseService = openCourseService;
        this.findAllOpenCourseService = findAllOpenCourseService;
        this.updateCourseService = updateCourseService;
        this.deleteCourseService = deleteCourseService;
        this.findAllOpenCourseCurrentService = findAllOpenCourseCurrentService;
        this.allCourseService = allCourseService;
    }
}
