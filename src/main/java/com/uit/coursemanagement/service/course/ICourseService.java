package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.payload.course.AddNewCourseRequest;
import com.uit.coursemanagement.payload.course.LecturerRegisterCourseRequest;

import java.util.List;

public interface ICourseService{

    IFindAllCourseService<Void, List<CourseDto>> getFindAllCourseService();

    IAddNewCourseService<AddNewCourseRequest, Boolean> getAddNewCourseService();

    ILecturerRegisterCourseService<LecturerRegisterCourseRequest, Boolean> getLecturerRegisterCourseService();
}
