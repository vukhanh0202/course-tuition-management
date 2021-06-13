package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.dto.course.OpenCourseRegisterOfStudentDto;
import com.uit.coursemanagement.payload.course.*;

import java.util.List;

public interface ICourseService{

    IFindAllCourseService<String, List<CourseDto>> getFindAllCourseService();

    IAddNewCourseService<AddNewCourseRequest, Boolean> getAddNewCourseService();

    IOpenCourseService<OpenCourseRequest, Boolean> getOpenCourseService();

    IFindAllOpenCourseService<String, List<OpenCourseDto>> getFindAllOpenCourseService();

    IUpdateCourseService<UpdateCourseRequest, Boolean> getUpdateCourseService();

    IDeleteCourseService<Long, Boolean> getDeleteCourseService();

    IFindAllOpenCourseCurrentService<Long, List<OpenCourseRegisterDto>> getFindAllOpenCourseCurrentService();

    IAllCourseService<Void, List<CourseDto>> getAllCourseService();

    IUpdateOpenCourseService<UpdateOpenCourseRequest, Boolean> getUpdateOpenCourseService();

    IDeleteOpenCourseService<Long, Boolean> getDeleteOpenCourseService();

    IRegisterOpenCourseService<RegisterOpenCourseRequest, Boolean> getRegisterOpenCourseService();

    IDeleteRegisterOpenCourseService<RegisterOpenCourseRequest, Boolean> getDeleteRegisterOpenCourseService();

    IFindMyOpenCourseCurrentService<Long, OpenCourseRegisterOfStudentDto> getFindMyOpenCourseCurrentService();
}
