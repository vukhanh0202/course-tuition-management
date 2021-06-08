package com.uit.coursemanagement.service.lecturer.impl;

import com.uit.coursemanagement.service.lecturer.IAllUserLecturerService;
import com.uit.coursemanagement.service.lecturer.ILecturerService;
import com.uit.coursemanagement.service.student.IFindAllUserStudentService;
import com.uit.coursemanagement.service.student.IFindDetailStudentService;
import com.uit.coursemanagement.service.student.IStudentService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Slf4j
public class LecturerServiceImpl implements ILecturerService {

   private final IAllUserLecturerService allUserLecturerService;

    public LecturerServiceImpl(IAllUserLecturerService allUserLecturerService) {
        this.allUserLecturerService = allUserLecturerService;
    }
}
