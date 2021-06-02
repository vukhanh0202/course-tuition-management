package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;

import java.util.List;

public interface ICourseService{

    IFindAllCourseService<Void, List<CourseDto>> getFindAllCourseService();

    IAddNewCourseService<AddNewCourseRequest, Boolean> getAddNewCourseService();

    IOpenCourseService<OpenCourseRequest, Boolean> getOpenCourseService();

    IFindAllOpenCourseService<Void, List<OpenCourseDto>> getFindAllOpenCourseService();

}
