package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindDetailStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindDetailStudentServiceImpl extends AbstractBaseService<Long, StudentDetailDto>
        implements IFindDetailStudentService<Long, StudentDetailDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void preExecute(Long studentId) {
        User user = userRepository.findById(studentId).orElse(null);
        if (user == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.STUDENT)){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
    }

    @Override
    public StudentDetailDto doing(Long studentId) {
        return studentMapper.toStudentDetailDto(userRepository.findById(studentId).orElse(null));
    }
}
