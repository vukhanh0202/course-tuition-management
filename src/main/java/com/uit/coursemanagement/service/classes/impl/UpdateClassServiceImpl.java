package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IUpdateClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateClassServiceImpl extends AbstractBaseService<UpdateClassRequest, Boolean>
        implements IUpdateClassService {

    private final ClassMapper classMapper;

    private final ClassRepository classRepository;

    @Override
    public void preExecute(UpdateClassRequest updateClassRequest) {
        ClassRoom classRoom = classRepository.findById(updateClassRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.ClassRoom.NOT_FOUND)));
        if (updateClassRequest.getName() != null && !updateClassRequest.getName().equals(classRoom.getName())) {
            if (classRepository.findByName(updateClassRequest.getName()).isPresent()) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.ClassRoom.EXIST, updateClassRequest.getName()));
            }
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
