package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.response.PaginationResponse;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllUserStudentServiceImpl extends AbstractBaseService<IFindAllUserStudentService.Input, PaginationResponse<StudentDto>>
        implements IFindAllUserStudentService<IFindAllUserStudentService.Input, PaginationResponse<StudentDto>> {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaginationResponse<StudentDto> doing(Input input) {
        Page<User> result = userRepository.findAllByUserType(EUserType.STUDENT, input.getPageable());
        return new PaginationResponse(
                result.getTotalElements()
                , result.getNumberOfElements()
                , result.getNumber() + 1
                , studentMapper.toStudentDtoList(result.getContent()));
    }

}
