package com.uit.coursemanagement.service.classes;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.classes.ClassDto;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.payload.classes.AddClassRequest;
import com.uit.coursemanagement.payload.semester.AddSemesterRequest;
import com.uit.coursemanagement.service.semester.IAddSemesterService;
import com.uit.coursemanagement.service.semester.IFindAllSemesterService;

import java.util.List;

public interface IClassService {

    IAddClassService<AddClassRequest, Boolean> getAddClassService();

    IFindAllClassService<Void, List<ClassDto>> getFindAllClassService();
}
