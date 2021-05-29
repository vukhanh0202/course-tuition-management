package com.uit.coursemanagement.service.student;


import com.uit.coursemanagement.dto.response.PaginationRequest;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindAllUserStudentService<Input, Output> extends IService<Input, Output> {

    /**
     * Param input.
     */
    @Data
    class Input extends PaginationRequest {
        public Input(Integer page, Integer size) {
            super(page, size);
        }
    }
}
