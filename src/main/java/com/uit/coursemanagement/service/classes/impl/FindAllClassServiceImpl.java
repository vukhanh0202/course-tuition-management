package com.uit.coursemanagement.service.classes.impl;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.mapper.classes.ClassMapper;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.repository.classes.ClassRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.classes.IFindAllClassService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllClassServiceImpl extends AbstractBaseService<String, List<ClassDto>>
        implements IFindAllClassService<String, List<ClassDto>> {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<ClassDto> doing(String className) {
        return classMapper.toClassDtoList(classRepository.findAllByNameContainingIgnoreCase(className));
    }

}
