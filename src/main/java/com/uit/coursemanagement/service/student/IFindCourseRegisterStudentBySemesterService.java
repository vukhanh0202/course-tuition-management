package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindCourseRegisterStudentBySemesterService<Input, Output> extends IService<Input, Output> {

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
