package com.uit.coursemanagement.service.course.impl;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.constant.enums.ECalendarShift;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.payload.course.OpenCourseRequest;
import com.uit.coursemanagement.payload.course.UpdateOpenCourseRequest;
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IOpenCourseService;
import com.uit.coursemanagement.service.course.IUpdateOpenCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UpdateOpenCourseServiceImpl extends AbstractBaseService<UpdateOpenCourseRequest, Boolean>
        implements IUpdateOpenCourseService<UpdateOpenCourseRequest, Boolean> {

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final SemesterRepository semesterRepository;

    public UpdateOpenCourseServiceImpl(OpenCourseMapper openCourseMapper, OpenCourseRepository openCourseRepository, CourseRepository courseRepository, UserRepository userRepository, SemesterRepository semesterRepository) {
        this.openCourseMapper = openCourseMapper;
        this.openCourseRepository = openCourseRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
    }


    @Override
    public void preExecute(UpdateOpenCourseRequest updateOpenCourseRequest) {
        if (updateOpenCourseRequest.getLecturerId() != null) {
            User user = userRepository.findById(updateOpenCourseRequest.getLecturerId()).orElse(null);
            if (user == null) {
                throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
            }
            if (!user.getUserType().equals(EUserType.LECTURER)) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
            }
        }
        if (updateOpenCourseRequest.getCourseId() != null) {
            if (courseRepository.findById(updateOpenCourseRequest.getCourseId()).isEmpty()) {
                throw new NotFoundException(messageHelper.getMessage(MessageCode.Course.NOT_FOUND));
            }
        }
        if (updateOpenCourseRequest.getSemesterId() != null) {
            Semester semester = semesterRepository.findById(updateOpenCourseRequest.getSemesterId()).orElse(null);
            if (semester == null) {
                throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND));
            }
            if (semester.getToDate().before(new Date(System.currentTimeMillis()))) {
                throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.INVALID));
            }
        }
        List<OpenCourse> openCourses = openCourseRepository
                .findAllBySemesterIdAndDayOfWeekAndClassRoomId(updateOpenCourseRequest.getSemesterId(), updateOpenCourseRequest.getDayOfWeek(), updateOpenCourseRequest.getClassId());
        if (!openCourses.isEmpty()) {
            openCourses.forEach(item -> {
                if (!item.getId().equals(updateOpenCourseRequest.getId())) {
                    List<ECalendarShift> shifts = item.getCalendarShifts();
                    shifts.forEach(shift -> {
                        if (updateOpenCourseRequest.getShifts().contains(shift)) {
                            throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.WAS_REGISTERED));
                        }
                    });
                }
            });
        }
        List<OpenCourse> openCourses2 = openCourseRepository
                .findAllByLecturerIdAndSemesterIdAndDayOfWeek(updateOpenCourseRequest.getLecturerId(), updateOpenCourseRequest.getSemesterId(), updateOpenCourseRequest.getDayOfWeek());
        if (!openCourses2.isEmpty()) {
            openCourses2.forEach(item -> {
                if (!item.getId().equals(updateOpenCourseRequest.getId())) {
                    List<ECalendarShift> shifts = item.getCalendarShifts();
                    shifts.forEach(shift -> {
                        if (updateOpenCourseRequest.getShifts().contains(shift)) {
                            throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.WAS_REGISTERED_IN_ANOTHER_IN_CLASS));
                        }
                    });
                }
            });
        }

    }

    @Override
    public Boolean doing(UpdateOpenCourseRequest updateOpenCourseRequest) {
        OpenCourse openCourse = openCourseRepository.findById(updateOpenCourseRequest.getId())
                .orElseThrow(() -> new NotFoundException(messageHelper.getMessage(MessageCode.OpenCourse.NOT_FOUND)));
        openCourseMapper.updateOpenCourse(updateOpenCourseRequest, openCourse);
        openCourseRepository.save(openCourse);
        return true;
    }

}
