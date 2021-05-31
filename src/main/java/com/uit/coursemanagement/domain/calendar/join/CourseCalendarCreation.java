//package com.uit.coursemanagement.domain.calendar.join;
//
//import com.uit.coursemanagement.constant.enums.ETypeCourse;
//import com.uit.coursemanagement.domain.SqlEntity;
//import com.uit.coursemanagement.domain.calendar.CalendarDetail;
//import com.uit.coursemanagement.domain.classes.ClassRoom;
//import com.uit.coursemanagement.domain.course.Course;
//import com.uit.coursemanagement.domain.student.Student;
//import com.uit.coursemanagement.domain.tuition.TuitionFee;
//import com.uit.coursemanagement.domain.user.User;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "course_calendar_creation")
//public class CourseCalendarCreation extends SqlEntity {
//
//    @EmbeddedId
//    private CourseCalendarCreationId id = new CourseCalendarCreationId();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("calendarDetailId")
//    @JoinColumn(name = "calendar_detail_id")
//    private CalendarDetail calendarDetail;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("classRoomId")
//    @JoinColumn(name = "class_room_id")
//    private ClassRoom classRoom;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("courseId")
//    @JoinColumn(name = "course_id")
//    private Course course;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("studentId")
//    @JoinColumn(name = "student_id")
//    private Student student;
//
//    @Enumerated(EnumType.STRING)
//    private ETypeCourse typeCourse;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseCalendarCreation")
//    private Set<TuitionFee> tuitionFees = new HashSet<>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        CourseCalendarCreation that = (CourseCalendarCreation) o;
//        return Objects.equals(calendarDetail, that.calendarDetail)
//                && Objects.equals(classRoom, that.classRoom)
//                && Objects.equals(course, that.course);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), calendarDetail, classRoom, course);
//    }
//}
