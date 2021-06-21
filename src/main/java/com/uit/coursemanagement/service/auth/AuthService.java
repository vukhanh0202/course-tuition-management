package com.uit.coursemanagement.service.auth;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.auth.UserPasswordDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.mapper.user.UserMapper;
import com.uit.coursemanagement.payload.student.CreateStudentRequest;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.service.AbstractBaseService;
import com.uit.coursemanagement.service.student.ICreateStudentService;
import com.uit.coursemanagement.utils.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageHelper messageHelper;

    public Boolean changePassword(UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userPasswordDto.getUserId())
                .orElseThrow(()-> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (!b.matches(userPasswordDto.getOldPassword(), user.getPassword())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.WRONG));
        }
        String pwdBcrypt = BCrypt.hashpw(userPasswordDto.getNewPassword(), BCrypt.gensalt(10));
        user.setPassword(pwdBcrypt);
        userRepository.save(user);
        return true;
    }

}
