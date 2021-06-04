package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.classes.IUpdateClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateClassServiceImpl extends AbstractBaseService<UpdateClassRequest, Boolean>
        implements IUpdateClassService<UpdateClassRequest, Boolean> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void preExecute(UpdateClassRequest updateClassRequest) {
        if (classRepository.findById(updateClassRequest.getId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.ClassRoom.NOT_FOUND));
        }
        if (classRepository.findByName(updateClassRequest.getName()).isPresent()) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.ClassRoom.EXIST, updateClassRequest.getName()));
        }
    }

    @Override
    public Boolean doing(UpdateClassRequest updateClassRequest) {
        ClassRoom classRoom = classRepository.findById(updateClassRequest.getId()).get();
        classMapper.updateClassRoom(updateClassRequest, classRoom);
        classRepository.save(classRoom);
        return true;
    }

}
