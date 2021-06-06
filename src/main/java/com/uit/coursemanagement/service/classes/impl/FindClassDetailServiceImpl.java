package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.dto.classes.ClassDetailDto;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IFindAllClassService;
import com.uit.coursemanagement.service.classes.IFindClassDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindClassDetailServiceImpl extends AbstractBaseService<IFindClassDetailService.Input, ClassDetailDto>
        implements IFindClassDetailService<IFindClassDetailService.Input, ClassDetailDto> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void preExecute(Input input) {
        if (classRepository.findById(input.getClassId()).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.ClassRoom.NOT_FOUND));
        }
    }

    @Override
    public ClassDetailDto doing(IFindClassDetailService.Input input) {
        return classMapper.toClassDetailDto(classRepository.findClassRoomBySemesterId(input.getClassId(),
                input.getSemesterId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND))));
    }
}
