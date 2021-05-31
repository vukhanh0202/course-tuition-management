package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.response.PaginationResponse;
import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.StudentDto;

import java.util.List;

public interface IStudentService {

    IFindAllUserStudentService<IFindAllUserStudentService.Input, PaginationResponse<StudentDto>> getFindAllUserStudentService();

    IFindDetailStudentService<Long, StudentDetailDto> getFindDetailStudentService();
}
