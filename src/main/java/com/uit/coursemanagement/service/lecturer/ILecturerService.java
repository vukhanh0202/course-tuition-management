package com.uit.coursemanagement.service.lecturer;


import com.uit.coursemanagement.dto.lecturer.LecturerDto;

import java.util.List;

public interface ILecturerService {

    IAllUserLecturerService<Void, List<LecturerDto>> getAllUserLecturerService();
}
