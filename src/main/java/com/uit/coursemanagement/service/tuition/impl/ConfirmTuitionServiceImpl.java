package com.uit.coursemanagement.service.tuition.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.tuition.IConfirmTuitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ConfirmTuitionServiceImpl extends AbstractBaseService<IConfirmTuitionService.Input, Boolean>
        implements IConfirmTuitionService<IConfirmTuitionService.Input, Boolean> {

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    public void preExecute(Input input) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(input.getTuitionId()).orElse(null);
        if (tuitionFee == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Tuition.NOT_FOUND));
        }
        if (!tuitionFee.getStatus().equals(EStatus.PENDING)){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Tuition.IS_COMPLETED));
        }
    }

    @Override
    public Boolean doing(Input input) {
        TuitionFee tuitionFee = tuitionFeeRepository.findById(input.getTuitionId()).get();
        List<StudentCourse> studentCourses = userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(tuitionFee.getStudent().getId(), tuitionFee.getSemester().getId());

        List<TuitionFee> tuitionFees = tuitionFeeRepository
                .findAllByStudentIdAndSemesterIdAndStatus(tuitionFee.getStudent().getId(), tuitionFee.getSemester().getId(),EStatus.COMPLETED);
        AtomicReference<Double> totalFee = new AtomicReference<>(0d);
        studentCourses.forEach(item->{
            totalFee.set(totalFee.get() + item.getPriceBasic() * item.getCreditQuantity());
        });
        Double totalFeeCompleted = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
        if (totalFeeCompleted >= totalFee.get()){
            tuitionFee.setStatus(EStatus.CLOSED);
        }else{
            tuitionFee.setStatus(EStatus.COMPLETED);
        }
        tuitionFeeRepository.save(tuitionFee);
        return true;
    }

}
