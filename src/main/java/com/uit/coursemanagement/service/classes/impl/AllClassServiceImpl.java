package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IAddClassService;
import com.uit.coursemanagement.service.classes.IAllClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AllClassServiceImpl extends AbstractBaseService<Void, List<ClassDto>>
        implements IAllClassService<Void, List<ClassDto>> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<ClassDto> doing(Void unused) {
        return classMapper.toClassBasicDtoList(classRepository.findAll());
    }
}
