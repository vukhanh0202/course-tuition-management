package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrStudentCode(String username, String code);

//    List<User> findAllByUserType(EUserType userType, Pageable pageable);
    Long countAllByUserType(EUserType userType);

    List<User> findAllByUserTypeAndFullNameContaining(EUserType userType, String fullName);

    List<User> findAllByUserType(EUserType userType);

}


