package com.uit.coursemanagement.mapper.student;

import com.uit.coursemanagement.constant.enums.EStatus;
import com.uit.coursemanagement.domain.course.OpenCourse;
import com.uit.coursemanagement.domain.semester.Semester;
import com.uit.coursemanagement.domain.student.join.StudentCourse;
import com.uit.coursemanagement.domain.tuition.TuitionFee;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.student.StudentDetailDto;
import com.uit.coursemanagement.dto.student.StudentDto;
import com.uit.coursemanagement.dto.student.join.CourseSemesterStudentDto;
import com.uit.coursemanagement.mapper.MapperBase;
import com.uit.coursemanagement.mapper.course.OpenCourseMapper;
import com.uit.coursemanagement.payload.student.UpdateStudentRequest;
import com.uit.coursemanagement.repository.semester.SemesterRepository;
import com.uit.coursemanagement.repository.user.TuitionFeeRepository;
import com.uit.coursemanagement.repository.user.UserCourseRepository;
import com.uit.coursemanagement.utils.ConvertDoubleToString;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Mapper(componentModel = "spring")
public abstract class StudentMapper implements MapperBase {

    @Autowired
    private TuitionFeeRepository tuitionFeeRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private OpenCourseMapper openCourseMapper;

    //*************************************************
    //********** Mapper User To UserStudentDto (Search) **********
    //*************************************************
    @Named("toStudentDto")
    @BeforeMapping
    protected void toStudentDto(User user, @MappingTarget StudentDto studentDto) {
        studentDto.setCreditQuantityExperienced(user.getStudent().getStudentCourses().stream().mapToLong(StudentCourse::getCreditQuantity).sum()
                + "/" + user.getStudent().getTotalCreditQuantity());
        Date date = new Date();
        Semester semester = semesterRepository.findByDate(date).get();
        studentDto.setCreditQuantityPresent(userCourseRepository
                .findAllByStudentIdAndOpenCourseSemesterId(user.getId(), semester.getId()).stream().mapToLong(StudentCourse::getCreditQuantity).sum());
    }

    @BeanMapping(qualifiedByName = "toStudentDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toStudentDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.code", target = "code")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "student.schoolYear", target = "schoolYear")
//    @Mapping(source = "student.creditQuantityPresent", target = "creditQuantityPresent")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "student.dateOfBirth", target = "dateOfBirth")
    public abstract StudentDto toStudentDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toStudentDtoList")
    public abstract List<StudentDto> toStudentDtoList(List<User> users);

    //*************************************************
    //********** Mapper User To UserStudentDto (Search) **********
    //*************************************************
    @Named("toStudentDetailDto")
    @BeforeMapping
    protected void toStudentDetailDto(User user, @MappingTarget StudentDetailDto studentDetailDto) {
        List<StudentCourse> studentCourses = user.getStudent().getStudentCourses();
        List<CourseSemesterStudentDto> result = new ArrayList<>();
        Map<Long, List<OpenCourse>> map = new HashMap<>();
        studentCourses.forEach(studentCourse -> {
            Long semesterId = studentCourse.getOpenCourse().getSemester().getId();
            if (map.containsKey(semesterId)) {
                List<OpenCourse> courses = map.get(semesterId);
                courses.add(studentCourse.getOpenCourse());
            } else {
                map.put(semesterId, List.of(studentCourse.getOpenCourse()));
            }
        });
        List<TuitionFee> tuitionFees = tuitionFeeRepository
                .findAllByStudentIdAndStatus(user.getId(), EStatus.COMPLETED);
        // Sort by timeCompleted DESC
        tuitionFees.sort(new Comparator<TuitionFee>() {
            @Override
            public int compare(TuitionFee o1, TuitionFee o2) {
                // sort DESC
                if (o2.getTimeCompleted().after(o1.getTimeCompleted())) {
                    return 1;
                } else if (o1.getTimeCompleted().after(o2.getTimeCompleted())) {
                    return -1;
                }
                return 0;
            }
        });
        AtomicReference<Double> totalFee = new AtomicReference<>(0d);
        studentCourses.forEach(item->{
            totalFee.set(totalFee.get() + item.getPriceBasic() * item.getCreditQuantity());
        });
        Double totalCompleted = tuitionFees.stream().mapToDouble(TuitionFee::getTotalFee).sum();
        Double totalDebt = totalFee.get() - totalCompleted;
        Set<Long> set = map.keySet();
        for (Long key : set) {
            Semester semester = semesterRepository.findById(key).get();
            CourseSemesterStudentDto item = new CourseSemesterStudentDto();
            item.setSemesterId(semester.getId());
            item.setSemesterName(semester.getName());
            item.setFromDate(semester.getFromDate());
            item.setToDate(semester.getToDate());
            result.add(item);
        }
        studentDetailDto.setList(result);
        studentDetailDto.setTotalFee(ConvertDoubleToString.convert(totalFee.get()));
        studentDetailDto.setTotalFeeCompleted(ConvertDoubleToString.convert(totalCompleted));
        studentDetailDto.setTotalFeeDebt(ConvertDoubleToString.convert(totalDebt));
        studentDetailDto.setTotalCreditQuantityExperience(studentCourses.stream().mapToLong(StudentCourse::getCreditQuantity).sum());
    }

    @BeanMapping(qualifiedByName = "toStudentDetailDto", ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Named("toStudentDetailDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "student.code", target = "code")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "student.schoolYear", target = "schoolYear")
    @Mapping(source = "student.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "student.faculty", target = "faculty")
    @Mapping(source = "student.trainingSystem", target = "trainingSystem")
    public abstract StudentDetailDto toStudentDetailDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @IterableMapping(qualifiedByName = "toStudentDetailDtoList")
    public abstract List<StudentDetailDto> toStudentDetailDtoList(List<User> users);

    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "dateOfBirth", target = "student.dateOfBirth")
    @Mapping(source = "faculty", target = "student.faculty")
    @Mapping(source = "trainingSystem", target = "student.trainingSystem")
    public abstract void updateStudent(UpdateStudentRequest dto, @MappingTarget User entity);
}
