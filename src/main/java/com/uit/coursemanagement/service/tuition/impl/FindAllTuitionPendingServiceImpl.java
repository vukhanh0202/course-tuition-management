package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.tuition.TuitionPendingDto;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.tuition.TuitionMapper;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionPendingStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllTuitionPendingServiceImpl extends AbstractBaseService<IFindAllTuitionPendingStudentService.Input, List<TuitionPendingDto>>
        implements IFindAllTuitionPendingStudentService {

    private final TuitionFeeRepository tuitionFeeRepository;

    private final TuitionMapper tuitionMapper;

    private final SemesterRepository semesterRepository;

    @Override
    public void preExecute(Input input) {
        if (input.getSemesterId() == null || semesterRepository.findById(input.getSemesterId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND));
        }
    }

    @Override
    public List<TuitionPendingDto> doing(Input input) {
        if (input.getFullName() == null){
            input.setFullName("");
        }
        List<TuitionFee> tuitionFees = tuitionFeeRepository.findAllByStudentUserFullNameContainingAndSemesterIdAndStatus(
                input.getFullName(), input.getSemesterId(), EStatus.PENDING);
        return tuitionMapper.toTuitionPendingList(tuitionFees);
    }

}
