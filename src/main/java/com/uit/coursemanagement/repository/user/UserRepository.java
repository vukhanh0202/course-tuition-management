package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrStudentCodeOrEmail(String username, String code, String email);

//    List<User> findAllByUserType(EUserType userType, Pageable pageable);
    Long countAllByUserType(EUserType userType);

    List<User> findAllByUserTypeAndFullNameContaining(EUserType userType, String fullName);

    List<User> findAllByUserType(EUserType userType);

    Optional<User> findByIdAndRoleId(Long id, ERoleType roleType);

}


