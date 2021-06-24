package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IAllSemesterService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AllSemesterServiceImpl extends AbstractBaseService<Void, List<SemesterDto>>
        implements IAllSemesterService<Void, List<SemesterDto>> {

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<SemesterDto> doing(Void unused) {
        return semesterMapper.toSemesterBasicDtoList(semesterRepository.findAllByStatusAndAndNameContainingOrderByFromDateDesc(EStatus.OPEN,""));
    }

}
