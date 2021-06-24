package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.response.PaginationResponse;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.mapper.student.StudentMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllSemesterServiceImpl extends AbstractBaseService<IFindAllSemesterService.Input, List<SemesterDto>>
        implements IFindAllSemesterService<IFindAllSemesterService.Input, List<SemesterDto>> {

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<SemesterDto> doing(Input input) {
        return semesterMapper.toSemesterDtoList(semesterRepository.findAllByStatusAndAndNameContainingOrderByFromDateDesc(input.getStatus(), input.getSearch()));
    }

}
