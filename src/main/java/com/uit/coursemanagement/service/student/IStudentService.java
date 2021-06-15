package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.dto.student.StudentFeeDto;
import com.uit.coursemanagement.dto.student.StudentTotalFeeDto;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;

import java.util.List;

public interface IStudentService {

    IFindAllUserStudentService<String, List<StudentDto>> getFindAllUserStudentService();

    IFindDetailStudentService<Long, StudentDetailDto> getFindDetailStudentService();

    IFindAllFeeStudentService<Long, List<StudentFeeDto>> getFindAllFeeStudentService();

    IPaymentFeeStudentService<PaymentFeeRequest, Boolean> getPaymentFeeStudentService();

    IFindTotalFeeStudentService<Long, StudentTotalFeeDto> getFindTotalFeeStudentService();
}
