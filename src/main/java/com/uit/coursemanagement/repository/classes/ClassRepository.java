package com.uit.coursemanagement.repository.classes;

import com.uit.coursemanagement.domain.classes.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassRoom, Long> {
   Optional<ClassRoom> findByName(String name);

   List<ClassRoom> findAllByNameContaining(String name);
}


