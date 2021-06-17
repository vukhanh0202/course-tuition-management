package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.dto.lecturer.LecturerFullDto;
import com.uit.coursemanagement.mapper.lecturer.LecturerMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.IAllUserLecturerService;
import com.uit.coursemanagement.service.lecturer.IFindAllUserLecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllUserLecturerServiceImpl extends AbstractBaseService<String, List<LecturerFullDto>>
        implements IFindAllUserLecturerService<String, List<LecturerFullDto>> {

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<LecturerFullDto> doing(String fullName) {
        List<User> result = userRepository.findAllByUserTypeAndFullNameContaining(EUserType.LECTURER,fullName);
        return lecturerMapper.toLecturerFullDtoList(result);
    }

}
