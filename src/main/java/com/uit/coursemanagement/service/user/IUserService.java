package com.uit.coursemanagement.service.user;


import com.uit.coursemanagement.dto.user.UserFullDto;

import java.util.List;

public interface IUserService {

    IFindAllUserService<Void, List<UserFullDto>> getFindAllUserService();
}
