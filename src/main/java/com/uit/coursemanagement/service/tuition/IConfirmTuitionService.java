package com.uit.coursemanagement.service.tuition;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IConfirmTuitionService extends IService<IConfirmTuitionService.Input, Boolean> {

    @Data
    class Input{
        private Long tuitionId;
        private Boolean confirm;

        public Input(Long tuitionId, Boolean confirm) {
            this.tuitionId = tuitionId;
            this.confirm = confirm;
        }
    }
}
