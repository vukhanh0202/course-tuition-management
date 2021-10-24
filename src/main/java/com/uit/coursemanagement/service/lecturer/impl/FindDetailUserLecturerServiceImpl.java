package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.lecturer.LecturerDetailDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.lecturer.LecturerMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.lecturer.IFindDetailUserLecturerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindDetailUserLecturerServiceImpl extends AbstractBaseService<Long, LecturerDetailDto>
        implements IFindDetailUserLecturerService {

    private final LecturerMapper lecturerMapper;

    private final UserRepository userRepository;

    @Override
    public void preExecute(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.LECTURER)){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
    }

    @Override
    public LecturerDetailDto doing(Long id) {
        return lecturerMapper.toLecturerDetailDto(userRepository.findById(id).get());
    }

}
