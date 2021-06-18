package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.dto.tuition.TuitionPending;
import com.uit.coursemanagement.mapper.tuition.TuitionMapper;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.tuition.IFindAllTuitionPendingStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllTuitionPendingServiceImpl extends AbstractBaseService<IFindAllTuitionPendingStudentService.Input, List<TuitionPending>>
        implements IFindAllTuitionPendingStudentService<IFindAllTuitionPendingStudentService.Input, List<TuitionPending>> {

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Autowired
    private TuitionMapper tuitionMapper;

    @Override
    public List<TuitionPending> doing(Input input) {
        List<TuitionFee> tuitionFees = tuitionFeeRepository.findAllByStudentUserFullNameContainingAndSemesterIdAndStatus(
                input.getFullName(), input.getSemesterId(), EStatus.PENDING);
        return tuitionMapper.toTuitionPendingList(tuitionFees);
    }

}
