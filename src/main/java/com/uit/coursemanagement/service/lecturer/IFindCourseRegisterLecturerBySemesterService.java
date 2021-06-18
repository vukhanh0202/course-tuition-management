package com.uit.coursemanagement.service.lecturer;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindCourseRegisterLecturerBySemesterService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input{

        private Long lecturerId;
        private Long semesterId;

        public Input(Long lecturerId, Long semesterId) {
            this.lecturerId = lecturerId;
            this.semesterId = semesterId;
        }
    }
}
