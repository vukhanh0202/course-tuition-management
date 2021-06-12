package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.response.PaginationRequest;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

public interface IFindAllCourseService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input extends PaginationRequest {
        private String fullName;

        public Input(Integer page, Integer size, String fullName) {
            super(page, size);
            this.fullName = fullName;
        }
    }
}
