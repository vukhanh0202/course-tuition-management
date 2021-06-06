package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.payload.classes.UpdateClassRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IDeleteClassService;
import com.uit.coursemanagement.service.classes.IUpdateClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteClassServiceImpl extends AbstractBaseService<Long, Boolean>
        implements IDeleteClassService<Long, Boolean> {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void preExecute(Long id) {
        ClassRoom classRoom = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.ClassRoom.NOT_FOUND)));
        if (classRoom.getOpenCourses().size() > 0 ) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.ClassRoom.NOT_DELETED));
        }
    }

    @Override
    public Boolean doing(Long id) {
        classRepository.deleteById(id);
        return true;
    }

}
