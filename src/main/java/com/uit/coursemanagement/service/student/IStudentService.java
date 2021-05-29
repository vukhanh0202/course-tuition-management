package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.StudentDto;

import java.util.List;

public interface IStudentService {

    IFindAllUserStudentService<IFindAllUserStudentService.Input, List<StudentDto>> getFindAllUserStudentService();

    ICountAllUserStudentService<Void, Long> getCountAllUserStudentService();

    IFindDetailStudentService<Long, StudentDetailDto> getFindDetailStudentService();
}
