package com.uit.coursemanagement.service.tuition;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.student.IFindCourseRegisterStudentBySemesterService;
import com.uit.coursemanagement.setup.builder.*;
import com.uit.coursemanagement.setup.wrapper.SemesterWrapper;
import com.uit.coursemanagement.setup.wrapper.TuitionFeeWrapper;
import com.uit.coursemanagement.setup.wrapper.UserCourseWrapper;
import com.uit.coursemanagement.setup.wrapper.UserWrapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TuitionControllerTest
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfirmStudentTuitionServiceTest {

    @Autowired
    private IConfirmStudentTuitionInSemesterStudentService confirmStudentTuitionInSemesterStudentService;

    @MockBean
    private TuitionFeeRepository tuitionFeeRepository;

    @MockBean
    private SemesterRepository semesterRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserCourseRepository userCourseRepository;

    @Test
    public void execute_UTCID01_StudentNotFound_ExceptionFail() {
        Long studentId = null;
        Long semesterId = null;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID02_StudentNotFound_ExceptionFail() {
        Long studentId = null;
        Long semesterId = -1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID03_StudentNotFound_ExceptionFail() {
        Long studentId = null;
        Long semesterId = 1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID04_StudentNotFound_ExceptionFail() {
        Long studentId = -1L;
        Long semesterId = null;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID05_StudentNotFound_ExceptionFail() {
        Long studentId = -1L;
        Long semesterId = -1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID06_StudentNotFound_ExceptionFail() {
        Long studentId = -1L;
        Long semesterId = 1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, null, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID07_SemesterNotFound_ExceptionFail() {
        Long studentId = 1L;
        Long semesterId = null;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository))
                .user(new UserWrapper(user, user, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID08_SemesterNotFound_ExceptionFail() {
        Long studentId = 1L;
        Long semesterId = -1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository))
                .user(new UserWrapper(user, user, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertThrows(NotFoundException.class, () -> confirmStudentTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID09_GoodInput_Success() {
        Long studentId = 1L;
        Long semesterId = 1L;
        var user = new UserBuilder().id(studentId).userType(EUserType.STUDENT).build();
        var userCourse = new UserCourseBuilder().semesterId(semesterId).studentId(studentId)
                .creditQuantity(1L).priceBasic(1D).classId(1L).build();
        var semester = new SemesterBuilder().id(semesterId).build();
        var tuitionFee = new TuitionFeeBuilder().id(1L).status(EStatus.PENDING).studentId(1L)
                .totalFee(1D).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository))
                .user(new UserWrapper(user, user, userRepository))
                .userCourse(new UserCourseWrapper(userCourse, userCourse, userCourseRepository))
                .tuitionFee(new TuitionFeeWrapper(tuitionFee, tuitionFee, tuitionFeeRepository))
                .build();
        var input = new IConfirmStudentTuitionInSemesterStudentService.Input(semester.getId(), user.getId());
        Assertions.assertNotNull(confirmStudentTuitionInSemesterStudentService.execute(input));
    }

}
