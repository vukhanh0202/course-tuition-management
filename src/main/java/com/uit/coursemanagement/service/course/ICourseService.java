package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;

import java.util.List;

public interface ICourseService{

    IFindAllCourseService<Void, List<CourseDto>> getFindAllCourseService();
}
