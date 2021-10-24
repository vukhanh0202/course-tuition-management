package com.uit.coursemanagement.service.classes;


import com.uit.coursemanagement.dto.classes.ClassDetailDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindClassDetailService extends IService<IFindClassDetailService.Input, ClassDetailDto> {

    @Data
    class Input {
        private Long classId;
        private Long semesterId;

        public Input(Long classId, Long semesterId) {
            this.classId = classId;
            this.semesterId = semesterId;
        }
    }
}
