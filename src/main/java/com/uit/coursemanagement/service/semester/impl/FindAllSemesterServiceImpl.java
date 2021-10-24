package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllSemesterServiceImpl extends AbstractBaseService<IFindAllSemesterService.Input, List<SemesterDto>>
        implements IFindAllSemesterService {

    private final SemesterMapper semesterMapper;

    private final SemesterRepository semesterRepository;

    @Override
    public List<SemesterDto> doing(Input input) {
        return semesterMapper.toSemesterDtoList(semesterRepository.findAllByStatusAndAndNameContainingOrderByFromDateDesc(input.getStatus(), input.getSearch()));
    }

}
