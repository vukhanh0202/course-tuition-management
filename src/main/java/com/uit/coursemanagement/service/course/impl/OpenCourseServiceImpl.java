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
import com.uit.coursemanagement.repository.course.CourseRepository;
import com.uit.coursemanagement.repository.course.OpenCourseRepository;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.course.IOpenCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenCourseServiceImpl extends AbstractBaseService<OpenCourseRequest, Boolean>
        implements IOpenCourseService {

    private final OpenCourseMapper openCourseMapper;

    private final OpenCourseRepository openCourseRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final SemesterRepository semesterRepository;

    @Override
    public void preExecute(OpenCourseRequest openCourseRequest) {
        User user = userRepository.findById(openCourseRequest.getLecturerId()).orElse(null);
        if (user == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        if (!user.getUserType().equals(EUserType.LECTURER)){
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
        if (courseRepository.findById(openCourseRequest.getCourseId()).isEmpty()){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Course.NOT_FOUND));
        }
        Semester semester = semesterRepository.findById(openCourseRequest.getSemesterId()).orElse(null);
        if (semester == null){
            throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.NOT_FOUND));
        }
//        if (semester.getToDate().before(new Date(System.currentTimeMillis()))){
//            throw new NotFoundException(messageHelper.getMessage(MessageCode.Semester.INVALID));
//        }
        List<OpenCourse> openCourses = openCourseRepository
                .findAllBySemesterIdAndDayOfWeekAndClassRoomId(openCourseRequest.getSemesterId(),openCourseRequest.getDayOfWeek(),openCourseRequest.getClassId());
        if (!openCourses.isEmpty()){
            openCourses.forEach(item ->{
                List<ECalendarShift> shifts = item.getCalendarShifts();
                shifts.forEach(shift ->{
                    if (openCourseRequest.getShifts().contains(shift)){
                        throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.WAS_REGISTERED));
                    }
                });
            });
        }

        List<OpenCourse> openCourses2 = openCourseRepository
                .findAllByLecturerIdAndSemesterIdAndDayOfWeek(openCourseRequest.getLecturerId(),openCourseRequest.getSemesterId(),openCourseRequest.getDayOfWeek());
        if (!openCourses2.isEmpty()){
            openCourses2.forEach(item ->{
                List<ECalendarShift> shifts = item.getCalendarShifts();
                shifts.forEach(shift ->{
                    if (openCourseRequest.getShifts().contains(shift)){
                        throw new InvalidException(messageHelper.getMessage(MessageCode.OpenCourse.WAS_REGISTERED_IN_ANOTHER_IN_CLASS));
                    }
                });
            });
        }
    }

    @Override
    public Boolean doing(OpenCourseRequest openCourseRequest) {
        openCourseRepository.save(openCourseMapper.toOpenCourse(openCourseRequest));
        return true;
    }

}
