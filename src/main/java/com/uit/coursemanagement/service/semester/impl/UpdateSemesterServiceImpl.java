package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IUpdateSemesterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateSemesterServiceImpl extends AbstractBaseService<UpdateSemesterRequest, Boolean>
        implements IUpdateSemesterService {

    private final SemesterMapper semesterMapper;

    private final SemesterRepository semesterRepository;

    @Override
    public void preExecute(UpdateSemesterRequest updateSemesterRequest) {
        if (updateSemesterRequest.getFromDate().after(updateSemesterRequest.getToDate())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.DATE_INVALID));
        }
        if (semesterRepository.findAllByFromDateAndToDate(updateSemesterRequest.getFromDate(),
                updateSemesterRequest.getToDate()).size() > 0){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.EXIST_TIME, updateSemesterRequest.getName()));
        }
    }

    @Override
    public Boolean doing(UpdateSemesterRequest updateSemesterRequest) {
        Semester semester = semesterRepository.findById(updateSemesterRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        List<Semester> list = semesterRepository.findAllByFromDateAndToDate(updateSemesterRequest.getFromDate(),
                updateSemesterRequest.getToDate());
        if (!list.isEmpty()) {
            if (list.size() == 1 && !list.get(0).getId().equals(updateSemesterRequest.getId()))
                throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.EXIST_TIME, updateSemesterRequest.getName()));
        }
        semesterMapper.updateSemester(updateSemesterRequest, semester);
        semesterRepository.save(semester);
        return true;
    }

}
