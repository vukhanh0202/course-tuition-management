package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;

import java.util.List;

public interface ICourseService{

    IFindAllCourseService<String, List<CourseDto>> getFindAllCourseService();

    IAddNewCourseService<AddNewCourseRequest, Boolean> getAddNewCourseService();

    IOpenCourseService<OpenCourseRequest, Boolean> getOpenCourseService();

    IFindAllOpenCourseService<String, List<OpenCourseDto>> getFindAllOpenCourseService();

    IUpdateCourseService<UpdateCourseRequest, Boolean> getUpdateCourseService();

    IDeleteCourseService<Long, Boolean> getDeleteCourseService();

    IFindAllOpenCourseCurrentService<String, List<OpenCourseDto>> getFindAllOpenCourseCurrentService();

    IAllCourseService<Void, List<CourseDto>> getAllCourseService();

    IUpdateOpenCourseService<UpdateOpenCourseRequest, Boolean> getUpdateOpenCourseService();

    IDeleteOpenCourseService<Long, Boolean> getDeleteOpenCourseService();
}
