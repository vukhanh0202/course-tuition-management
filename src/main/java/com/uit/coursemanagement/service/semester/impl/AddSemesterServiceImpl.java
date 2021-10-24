package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddSemesterServiceImpl extends AbstractBaseService<AddSemesterRequest, Boolean>
        implements IAddSemesterService {

    private final SemesterMapper semesterMapper;

    private final SemesterRepository semesterRepository;

    @Override
    public void preExecute(AddSemesterRequest addSemesterRequest) {
        if (addSemesterRequest.getFromDate().after(addSemesterRequest.getToDate())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.DATE_INVALID));
        }
        if (semesterRepository.findAllByFromDateAndToDate(addSemesterRequest.getFromDate(),
                addSemesterRequest.getToDate()).size() > 0){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.EXIST_TIME, addSemesterRequest.getName()));
        }
    }

    @Override
    public Boolean doing(AddSemesterRequest addSemesterRequest) {
        semesterRepository.save(semesterMapper.toSemester(addSemesterRequest));
        return true;
    }

}
