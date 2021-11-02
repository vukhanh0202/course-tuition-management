package com.uit.coursemanagement.setup.builder;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.Student;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;

/**
 * TuitionFeeBuilder
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
public class UserCourseBuilder {
    private Long studentId;
    private Long semesterId;


    public UserCourseBuilder studentId(Long studentId){
        this.studentId = studentId;
        return this;
    }

    public UserCourseBuilder semesterId(Long semesterId){
        this.semesterId = semesterId;
        return this;
    }

    public StudentCourse build() {
        StudentCourse studentCourse = new StudentCourse();
        Student student = new Student();
        student.setId(studentId);
        OpenCourse openCourse = new OpenCourse();
        Semester semester = new Semester();
        semester.setId(semesterId);
        openCourse.setSemester(semester);
        studentCourse.setStudent(student);
        studentCourse.setOpenCourse(openCourse);
        return studentCourse;
    }
}
