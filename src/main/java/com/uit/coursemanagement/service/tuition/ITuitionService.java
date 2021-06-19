package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.dto.tuition.TuitionDto;
import com.uit.coursemanagement.dto.tuition.TuitionPendingDto;

import java.util.List;

public interface ITuitionService {

    IFindAllTuitionPendingStudentService<IFindAllTuitionPendingStudentService.Input, List<TuitionPendingDto>> getFindAllTuitionPendingStudentService();

    IConfirmTuitionService<IConfirmTuitionService.Input, Boolean> getConfirmTuitionService();

    IFindAllTuitionInSemesterStudentService<IFindAllTuitionInSemesterStudentService.Input, List<TuitionDto>> getFindAllTuitionInSemesterStudentService();

    IConfirmStudentTuitionInSemesterStudentService<IConfirmStudentTuitionInSemesterStudentService.Input, Boolean> getConfirmStudentTuitionInSemesterStudentService();
}
