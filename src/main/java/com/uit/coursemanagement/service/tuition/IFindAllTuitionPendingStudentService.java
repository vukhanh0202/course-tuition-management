package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindAllTuitionPendingStudentService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input{
        private Long semesterId;
        private String fullName;

        public Input(Long semesterId, String fullName) {
            this.semesterId = semesterId;
            this.fullName = fullName;
        }
    }
}
