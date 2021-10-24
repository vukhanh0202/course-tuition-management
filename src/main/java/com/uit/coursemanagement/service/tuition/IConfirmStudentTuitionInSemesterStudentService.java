package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IConfirmStudentTuitionInSemesterStudentService extends IService<IConfirmStudentTuitionInSemesterStudentService.Input, Boolean> {

    @Data
    class Input{
        private Long semesterId;
        private Long studentId;

        public Input(Long semesterId, Long studentId) {
            this.semesterId = semesterId;
            this.studentId = studentId;
        }
    }
}
