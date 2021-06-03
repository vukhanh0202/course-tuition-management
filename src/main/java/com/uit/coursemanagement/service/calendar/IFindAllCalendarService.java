package com.uit.coursemanagement.service.calendar;


import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.Date;

public interface IFindAllCalendarService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input{
        private Date fromDate;
        private Date toDate;

        public Input(Date fromDate, Date toDate) {
            this.fromDate = fromDate;
            this.toDate = toDate;
        }
    }
}
