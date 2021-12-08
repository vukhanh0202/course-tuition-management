package com.uit.coursemanagement.service.tuition;

import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.setup.builder.MockBuilder;
import com.uit.coursemanagement.setup.builder.SemesterBuilder;
import com.uit.coursemanagement.setup.wrapper.SemesterWrapper;
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
public class FindAllTuitionInSemesterServiceTest {

    @Autowired
    private IFindAllTuitionInSemesterStudentService findAllTuitionInSemesterStudentService;

    @MockBean
    private SemesterRepository semesterRepository;

    @Test
    public void execute_UTCID01_SemesterNotFound_ExceptionFail() {
        Long semesterId = null;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID02_SemesterNotFound_ExceptionFail() {
        Long semesterId = null;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID03_SemesterNotFound_ExceptionFail() {
        Long semesterId = null;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID04_SemesterNotFound_ExceptionFail() {
        var semesterId = -1L;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID05_SemesterNotFound_ExceptionFail() {
        var semesterId = -1L;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID06_SemesterNotFound_ExceptionFail() {
        var semesterId = -1L;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID07_SemesterNotFound_ExceptionFail() {
        var semesterId = 0L;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID08_SemesterNotFound_ExceptionFail() {
        var semesterId = 0L;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID09_SemesterNotFound_ExceptionFail() {
        var semesterId = 0L;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, null, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertThrows(NotFoundException.class, () -> findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID010_GoodInput_Success() {
        var semesterId = 1L;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID011_GoodInput_Success() {
        var semesterId = 1L;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID012_GoodInput_Success() {
        var semesterId = 1L;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID013_GoodInput_Success() {
        var semesterId = 9L;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID014_GoodInput_Success() {
        var semesterId = 9L;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID015_GoodInput_Success() {
        var semesterId = 9L;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID016_GoodInput_Success() {
        var semesterId = 10L;
        String fullName = null;
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID017_GoodInput_Success() {
        var semesterId = 10L;
        var fullName = "Vũ Khánh";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

    @Test
    public void execute_UTCID018_GoodInput_Success() {
        var semesterId = 10L;
        var fullName = "abcxzy";
        var semester = new SemesterBuilder().id(semesterId).build();
        new MockBuilder().semester(new SemesterWrapper(semester, semester, semesterRepository)).build();
        var input = new IFindAllTuitionInSemesterStudentService.Input(semester.getId(), fullName);
        Assertions.assertNotNull(findAllTuitionInSemesterStudentService.execute(input));
    }

}
