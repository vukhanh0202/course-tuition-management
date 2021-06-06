package com.uit.coursemanagement.repository.classes;

import com.uit.coursemanagement.domain.classes.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassRoom, Long> {
   Optional<ClassRoom> findByName(String name);

   List<ClassRoom> findAllByNameContainingIgnoreCase(String name);

   @Query(value = "SELECT * \n" +
           " FROM class_room cr, open_course oc" +
           " where cr.id = oc.class_id" +
           " AND oc.semester_id = :semesterId" +
           " AND cr.id = :classId",
           nativeQuery = true)
   Optional<ClassRoom> findClassRoomBySemesterId(Long classId, Long semesterId);
}


