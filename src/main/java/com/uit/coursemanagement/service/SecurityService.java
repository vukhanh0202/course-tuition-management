package com.uit.coursemanagement.service;

import com.uit.coursemanagement.constant.enums.ERoleType;
import com.uit.coursemanagement.data.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean hasRole(String eRoleType) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getRoleCode().equals(ERoleType.valueOf(eRoleType));
    }
}
