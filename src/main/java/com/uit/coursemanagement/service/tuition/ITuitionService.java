package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.dto.tuition.TuitionPending;

import java.util.List;

public interface ITuitionService {

    IFindAllTuitionPendingStudentService<IFindAllTuitionPendingStudentService.Input, List<TuitionPending>> getFindAllTuitionPendingStudentService();

    IConfirmTuitionService<IConfirmTuitionService.Input, Boolean> getConfirmTuitionService();
}
