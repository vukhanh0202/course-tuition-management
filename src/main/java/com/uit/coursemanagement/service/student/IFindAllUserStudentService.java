package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.response.PaginationRequest;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.List;

public interface IFindAllUserStudentService extends IService<String, List<StudentDto>> {

}
