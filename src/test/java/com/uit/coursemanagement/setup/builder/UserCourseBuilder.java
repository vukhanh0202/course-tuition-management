package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.constant.enums.EDayOfWeek;
import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.classes.ClassRoom;
import com.uit.coursemanagement.domain.course.Course;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;

import java.util.ArrayList;

/**
 * TuitionFeeBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class UserCourseBuilder {
    private Long studentId;
    private Long semesterId;
    private Long creditQuantity;
    private Double priceBasic;
    private Long classId;


    public UserCourseBuilder studentId(Long studentId){
        this.studentId = studentId;
        return this;
    }

    public UserCourseBuilder semesterId(Long semesterId){
        this.semesterId = semesterId;
        return this;
    }
    public UserCourseBuilder creditQuantity(Long creditQuantity){
        this.creditQuantity = creditQuantity;
        return this;
    }

    public UserCourseBuilder priceBasic(Double priceBasic){
        this.priceBasic = priceBasic;
        return this;
    }

    public UserCourseBuilder classId(Long classId){
        this.classId = classId;
        return this;
    }

    public StudentCourse build() {
        StudentCourse studentCourse = new StudentCourse();
        Student student = new Student();
        student.setId(studentId);
        OpenCourse openCourse = new OpenCourse();
        Course course = new Course();
        course.setCreditQuantity(creditQuantity);
        course.setPriceBasic(priceBasic);
        Semester semester = new Semester();
        semester.setId(semesterId);
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(classId);
        classRoom.setName("class");
        openCourse.setSemester(semester);
        openCourse.setCourse(course);
        openCourse.setCalendarShifts(new ArrayList<>());
        openCourse.setClassRoom(classRoom);
        openCourse.setDayOfWeek(EDayOfWeek.FRIDAY);
        openCourse.setStudentCourses(new ArrayList<>());
        studentCourse.setCreditQuantity(creditQuantity);
        studentCourse.setPriceBasic(priceBasic);
        studentCourse.setStudent(student);
        studentCourse.setOpenCourse(openCourse);
        return studentCourse;
    }
}
