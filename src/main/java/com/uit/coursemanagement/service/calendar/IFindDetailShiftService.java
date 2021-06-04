package com.uit.coursemanagement.service.calendar;


import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.Date;

public interface IFindDetailShiftService<Input, Output> extends IService<Input, Output> {

    @Data
    class Input {
        private Date date;
        private ECalendarShift shift;

        public Input(Date date, ECalendarShift shift) {
            this.date = date;
            this.shift = shift;
        }
    }
}
