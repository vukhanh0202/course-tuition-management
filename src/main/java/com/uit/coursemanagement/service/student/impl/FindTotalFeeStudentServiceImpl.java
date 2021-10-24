package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentTotalFeeDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IFindTotalFeeStudentService;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindTotalFeeStudentServiceImpl extends AbstractBaseService<Long, StudentTotalFeeDto>
        implements IFindTotalFeeStudentService {

    private final UserRepository userRepository;

    private final UserCourseRepository userCourseRepository;

    private final TuitionFeeRepository tuitionFeeRepository;

    @Override
    public void preExecute(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
    }

    @Override
    public StudentTotalFeeDto doing(Long id) {
        StudentTotalFeeDto result = new StudentTotalFeeDto();
        List<StudentCourse> studentCourses = userCourseRepository.findAllByStudentId(id);
        List<TuitionFee> tuitionFees = tuitionFeeRepository.findAllByStudentIdAndStatus(id, EStatus.COMPLETED);

        AtomicReference<Double> totalFee = new AtomicReference<>(0d);
        studentCourses.forEach(item -> {
            totalFee.set(totalFee.get() + item.getCreditQuantity() * item.getPriceBasic());
        });
                studentCourses.stream().mapToDouble(StudentCourse::getPriceBasic).sum();
        Double completedFee = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();

        result.setCreditQuantity(studentCourses.stream().mapToLong(StudentCourse::getCreditQuantity).sum());
        result.setTotalFee(ConvertDoubleToString.convert(totalFee.get()));
        result.setFeeCompleted(ConvertDoubleToString.convert(completedFee));
        result.setFeeDebt(ConvertDoubleToString.convert(totalFee.get() - completedFee));
        return result;
    }

}
