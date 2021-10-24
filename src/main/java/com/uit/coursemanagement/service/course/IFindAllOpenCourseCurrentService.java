package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.OpenCourseRegisterDto;
import com.uit.coursemanagement.service.IService;

import java.util.List;

public interface IFindAllOpenCourseCurrentService extends IService<Long, List<OpenCourseRegisterDto>> {

}
