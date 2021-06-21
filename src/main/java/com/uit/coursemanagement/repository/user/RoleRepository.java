package com.uit.coursemanagement.repository.user;

import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.constant.enums.EUserType;
import com.uit.coursemanagement.domain.user.Role;
import com.uit.coursemanagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, ERoleType> {

}


