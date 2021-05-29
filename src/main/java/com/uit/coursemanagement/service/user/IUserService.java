package com.uit.coursemanagement.service.user;


import com.uit.coursemanagement.dto.user.UserStudentDto;

import java.util.List;

public interface IUserService {

    IFindAllUserStudentService<Void, List<UserStudentDto>> getFindAllUserStudentService();
}
