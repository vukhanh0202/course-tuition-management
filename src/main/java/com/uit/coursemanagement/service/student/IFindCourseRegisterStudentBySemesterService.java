package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.student.join.CourseSemesterStudentDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindCourseRegisterStudentBySemesterService extends IService<IFindCourseRegisterStudentBySemesterService.Input, CourseSemesterStudentDto> {

    @Data
    class Input{

        private Long studentId;
        private Long semesterId;

        public Input(Long studentId, Long semesterId) {
            this.studentId = studentId;
            this.semesterId = semesterId;
        }
    }
}
