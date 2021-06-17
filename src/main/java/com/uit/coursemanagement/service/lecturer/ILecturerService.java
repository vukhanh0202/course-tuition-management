package com.uit.coursemanagement.service.lecturer;


import com.uit.coursemanagement.dto.lecturer.LecturerDetailDto;
import com.uit.coursemanagement.dto.lecturer.LecturerDto;
import com.uit.coursemanagement.dto.lecturer.LecturerFullDto;

import java.util.List;

public interface ILecturerService {

    IAllUserLecturerService<Void, List<LecturerDto>> getAllUserLecturerService();

    IFindDetailUserLecturerService<Long, LecturerDetailDto> getFindDetailUserLecturerService();

    IFindAllUserLecturerService<String, List<LecturerFullDto>> getFindAllUserLecturerService();
}
