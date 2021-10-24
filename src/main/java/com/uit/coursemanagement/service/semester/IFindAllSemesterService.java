package com.uit.coursemanagement.service.semester;


import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.dto.semester.SemesterDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.List;

public interface IFindAllSemesterService extends IService<IFindAllSemesterService.Input, List<SemesterDto>> {

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
