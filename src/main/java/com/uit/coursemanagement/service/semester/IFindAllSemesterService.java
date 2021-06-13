package com.uit.coursemanagement.service.semester;


import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindAllSemesterService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input{
        private EStatus status;
        private String search;

        public Input(EStatus status, String search) {
            this.status = status;
            this.search = search;
        }
    }
}
