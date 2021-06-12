package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import com.uit.coursemanagement.service.semester.IUpdateSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateSemesterServiceImpl extends AbstractBaseService<UpdateSemesterRequest, Boolean>
        implements IUpdateSemesterService<UpdateSemesterRequest, Boolean> {

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Boolean doing(UpdateSemesterRequest updateSemesterRequest) {
        Semester semester = semesterRepository.findById(updateSemesterRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        semesterMapper.updateSemester(updateSemesterRequest, semester);
        semesterRepository.save(semester);
        return true;
    }

}
