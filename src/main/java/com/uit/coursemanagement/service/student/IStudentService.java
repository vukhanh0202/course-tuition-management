package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.student.*;
import com.uit.coursemanagement.dto.student.join.CourseSemesterStudentDto;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.payload.tuition.PaymentFeeRequest;

import java.util.List;

public interface IStudentService {

    IFindAllUserStudentService<String, List<StudentDto>> getFindAllUserStudentService();

    IFindDetailStudentService<Long, StudentDetailDto> getFindDetailStudentService();

    IFindAllFeeStudentService<Long, List<StudentFeeDto>> getFindAllFeeStudentService();

    IPaymentFeeStudentService<PaymentFeeRequest, Boolean> getPaymentFeeStudentService();

    IFindTotalFeeStudentService<Long, StudentTotalFeeDto> getFindTotalFeeStudentService();

    IFindTimetableStudentService<Long, List<StudentTimetableDto>> getFindTimetableStudentService();

    IUpdateStudentService<UpdateStudentRequest, Boolean> getUpdateStudentService();

    IFindCourseRegisterStudentBySemesterService<IFindCourseRegisterStudentBySemesterService.Input, CourseSemesterStudentDto> getFindCourseRegisterStudentBySemesterService();

    ICreateStudentService<CreateStudentRequest, Boolean> getCreateStudentService();
}
