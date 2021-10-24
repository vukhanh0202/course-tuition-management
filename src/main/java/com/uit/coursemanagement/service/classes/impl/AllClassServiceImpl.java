package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IAllClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AllClassServiceImpl extends AbstractBaseService<Void, List<ClassDto>>
        implements IAllClassService {

    private final ClassMapper classMapper;

    private final ClassRepository classRepository;

    @Override
    public List<ClassDto> doing(Void unused) {
        return classMapper.toClassBasicDtoList(classRepository.findAll());
    }
}
