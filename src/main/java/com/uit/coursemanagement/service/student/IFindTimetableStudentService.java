package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.student.StudentTimetableDto;
import com.uit.coursemanagement.service.IService;

import java.util.List;

public interface IFindTimetableStudentService extends IService<Long, List<StudentTimetableDto>> {

}
