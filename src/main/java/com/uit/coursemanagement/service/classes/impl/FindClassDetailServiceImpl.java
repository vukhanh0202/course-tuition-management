package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.dto.classes.ClassDetailDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IFindClassDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindClassDetailServiceImpl extends AbstractBaseService<IFindClassDetailService.Input, ClassDetailDto>
        implements IFindClassDetailService {

    private final ClassMapper classMapper;

    private final ClassRepository classRepository;

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
