package com.uit.coursemanagement.service.semester;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.payload.semester.UpdateSemesterRequest;

import java.util.List;

public interface ISemesterService {

    IFindAllSemesterService<EStatus, List<SemesterDto>> getFindAllSemesterService();

    IAddSemesterService<AddSemesterRequest, Boolean> getAddSemesterService();

    IAllSemesterService<Void, List<SemesterDto>> getAllSemesterService();

    IUpdateSemesterService<UpdateSemesterRequest, Boolean> getUpdateSemesterService();

    IDeleteSemesterService<Long, Boolean> getDeleteSemesterService();
}
