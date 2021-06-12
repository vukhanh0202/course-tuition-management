package com.uit.coursemanagement.service.semester.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.semester.SemesterMapper;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.semester.IDeleteSemesterService;
import com.uit.coursemanagement.service.semester.IUpdateSemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteSemesterServiceImpl extends AbstractBaseService<Long, Boolean>
        implements IDeleteSemesterService<Long, Boolean> {

    @Autowired
    private SemesterMapper semesterMapper;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public void preExecute(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));
        if (semester.getOpenCourses().size()>0){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Semester.NOT_DELETED));
        }

    }

    @Override
    public Boolean doing(Long id) {
        Semester semester = semesterRepository.findById(id).get();
        semesterRepository.delete(semester);
        return true;
    }

}
