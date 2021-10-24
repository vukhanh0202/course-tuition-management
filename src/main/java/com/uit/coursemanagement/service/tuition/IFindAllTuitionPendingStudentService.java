package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.dto.tuition.TuitionPendingDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.List;

public interface IFindAllTuitionPendingStudentService extends IService<IFindAllTuitionPendingStudentService.Input, List<TuitionPendingDto>> {

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
