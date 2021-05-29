package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.user.UserStudentDto;

import java.util.List;

public interface IStudentService {

    IFindAllUserStudentService<IFindAllUserStudentService.Input, List<UserStudentDto>> getFindAllUserStudentService();

    ICountAllUserStudentService<Void, Long> getCountAllUserStudentService();
}
