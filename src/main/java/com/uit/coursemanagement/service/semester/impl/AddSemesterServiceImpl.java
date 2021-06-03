package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddSemesterServiceImpl extends AbstractBaseService<AddSemesterRequest, Boolean>
        implements IAddSemesterService<AddSemesterRequest, Boolean> {

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public void preExecute(AddSemesterRequest addSemesterRequest) {
        if (addSemesterRequest.getFromDate().after(addSemesterRequest.getToDate())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.INVALID));
        }
        if (semesterRepository.findAllByFromDateAndToDate(addSemesterRequest.getFromDate(),
                addSemesterRequest.getToDate()).size() > 0){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.EXIST, addSemesterRequest.getName()));
        }
    }

    @Override
    public Boolean doing(AddSemesterRequest addSemesterRequest) {
        semesterRepository.save(semesterMapper.toSemester(addSemesterRequest));
        return true;
    }

}
