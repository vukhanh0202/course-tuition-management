package com.uit.coursemanagement.service.course;


import com.uit.coursemanagement.dto.course.CourseDto;
import com.uit.coursemanagement.dto.response.PaginationRequest;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.List;

public interface IFindAllCourseService extends IService<String, List<CourseDto>> {

    @Data
    class Input extends PaginationRequest {
        private String fullName;

        public Input(Integer page, Integer size, String fullName) {
            super(page, size);
            this.fullName = fullName;
        }
    }
}
