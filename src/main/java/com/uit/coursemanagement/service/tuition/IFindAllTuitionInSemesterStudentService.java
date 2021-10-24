package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.dto.tuition.TuitionDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.List;

public interface IFindAllTuitionInSemesterStudentService extends IService<IFindAllTuitionInSemesterStudentService.Input, List<TuitionDto>> {

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
