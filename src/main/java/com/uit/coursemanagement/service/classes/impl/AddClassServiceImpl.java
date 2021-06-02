package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddClassServiceImpl extends AbstractBaseService<AddClassRequest, Boolean>
        implements IAddClassService<AddClassRequest, Boolean> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void preExecute(AddClassRequest addClassRequest) {
        if (classRepository.findByName(addClassRequest.getName()).isPresent()){
            throw new InvalidException(messageHelper.getMessage(MessageCode.ClassRoom.EXIST, addClassRequest.getName()));
        }
    }

    @Override
    public Boolean doing(AddClassRequest addClassRequest) {
        classRepository.save(classMapper.toClassRoom(addClassRequest));
        return true;
    }

}
