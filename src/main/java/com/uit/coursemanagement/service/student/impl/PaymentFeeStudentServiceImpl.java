package com.uit.coursemanagement.service.student.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.IPaymentFeeStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentFeeStudentServiceImpl extends AbstractBaseService<PaymentFeeRequest, Boolean>
        implements IPaymentFeeStudentService {

    private final UserRepository userRepository;

    private final SemesterRepository semesterRepository;

    private final TuitionFeeRepository tuitionFeeRepository;

    private final UserCourseRepository userCourseRepository;

    @Override
    public void preExecute(PaymentFeeRequest paymentFeeRequest) {
        User user = userRepository.findById(paymentFeeRequest.getStudentId()).orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        if (!user.getUserType().equals(EUserType.STUDENT)) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
        Semester semester = semesterRepository.findById(paymentFeeRequest.getSemesterId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));

        List<TuitionFee> tuitionFees = tuitionFeeRepository.findAllByStudentIdAndSemesterIdAndStatus(paymentFeeRequest.getStudentId(), semester.getId(), EStatus.COMPLETED);
        List<StudentCourse> studentCourses = userCourseRepository.findAllByStudentIdAndOpenCourseSemesterId(paymentFeeRequest.getStudentId(), paymentFeeRequest.getSemesterId());

        AtomicReference<Double> totalCourseToCompleted = new AtomicReference<>(0d);
        studentCourses.stream().forEach(item->{
            totalCourseToCompleted.set(totalCourseToCompleted.get() + item.getCreditQuantity() * item.getPriceBasic());
        });
        Double totalCourseToPayment = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
        if (totalCourseToPayment>= totalCourseToCompleted.get()){
            throw new InvalidException(messageHelper.getMessage(MessageCode.Student.PAYMENT_RESOLVED));
        }
    }

    @Override
    public Boolean doing(PaymentFeeRequest paymentFeeRequest) {
        Student student = userRepository.findById(paymentFeeRequest.getStudentId()).get().getStudent();
        Semester semester = semesterRepository.findById(paymentFeeRequest.getSemesterId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND)));

        List<StudentCourse> studentCourses = userCourseRepository.findAllByStudentIdAndOpenCourseSemesterId(paymentFeeRequest.getStudentId(), paymentFeeRequest.getSemesterId());
        Double totalCourseToCompleted = studentCourses.stream().mapToDouble(StudentCourse::getPriceBasic).sum();

        TuitionFee tuitionFee = new TuitionFee();
        tuitionFee.setStudent(student);
        tuitionFee.setSemester(semester);
        if (paymentFeeRequest.getTotalFee() >= totalCourseToCompleted) {
            tuitionFee.setTotalFee(totalCourseToCompleted);
        } else {
            tuitionFee.setTotalFee(paymentFeeRequest.getTotalFee());
        }
        tuitionFee.setTimeCompleted(new Date());
        tuitionFee.setStatus(EStatus.PENDING);
        tuitionFeeRepository.save(tuitionFee);
        return true;
    }

}
