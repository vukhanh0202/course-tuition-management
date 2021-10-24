package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllUserStudentServiceImpl extends AbstractBaseService<String, List<StudentDto>>
        implements IFindAllUserStudentService {

    private final StudentMapper studentMapper;

    private final UserRepository userRepository;

    @Override
    public List<StudentDto> doing(String fullName) {
        List<User> result = userRepository.findAllByUserTypeAndFullNameContaining(EUserType.STUDENT,fullName);
        return studentMapper.toStudentDtoList(result);
    }

}
