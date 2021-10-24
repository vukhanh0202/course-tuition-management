package com.uit.coursemanagement.service.calendar;


import com.uit.coursemanagement.dto.calendar.CalendarDto;
import com.uit.coursemanagement.service.IService;
import lombok.Data;

import java.util.Date;
import java.util.List;

public interface IFindAllCalendarService extends IService<IFindAllCalendarService.Input, List<CalendarDto>> {

    @Data
    class Input{
        private Date fromDate;
        private Date toDate;
        private String classRoom;
        private String courseName;

        public Input(Date fromDate, Date toDate, String classRoom, String courseName) {
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.classRoom = classRoom;
            this.courseName = courseName;
        }
    }
}
