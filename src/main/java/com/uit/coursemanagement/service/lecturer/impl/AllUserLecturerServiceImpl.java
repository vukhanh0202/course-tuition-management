package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.mapper.lecturer.LecturerMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.IAllUserLecturerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllUserLecturerServiceImpl extends AbstractBaseService<Void, List<LecturerDto>>
        implements IAllUserLecturerService {

    private final LecturerMapper lecturerMapper;

    private final UserRepository userRepository;

    @Override
    public List<LecturerDto> doing(Void unused) {
        List<User> result = userRepository.findAllByUserType(EUserType.LECTURER);
        return lecturerMapper.toLecturerDtoList(result);
    }

}
