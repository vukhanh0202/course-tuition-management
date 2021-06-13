package com.uit.coursemanagement.payload.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class RegisterOpenCourseRequest {

   private List<Long> list;

   @JsonIgnore
   private Long userId;
}
