package com.uit.coursemanagement.service.lecturer;


import com.uit.coursemanagement.dto.lecturer.LecturerDetailDto;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.dto.lecturer.LecturerFullDto;
import com.uit.coursemanagement.dto.lecturer.join.CourseSemesterLecturerDto;
import com.uit.coursemanagement.payload.lecturer.CreateLecturerRequest;
import com.uit.coursemanagement.payload.lecturer.UpdateLecturerRequest;

import java.util.List;

public interface ILecturerService {

    IAllUserLecturerService<Void, List<LecturerDto>> getAllUserLecturerService();

    IFindDetailUserLecturerService<Long, LecturerDetailDto> getFindDetailUserLecturerService();

    IFindAllUserLecturerService<String, List<LecturerFullDto>> getFindAllUserLecturerService();

    IFindCourseRegisterLecturerBySemesterService<IFindCourseRegisterLecturerBySemesterService.Input, CourseSemesterLecturerDto> getFindCourseRegisterLecturerBySemesterService();

    ICreateLecturerService<CreateLecturerRequest, Boolean> getCreateLecturerService();

    IUpdateLecturerService<UpdateLecturerRequest, Boolean> getUpdateLecturerService();
}
