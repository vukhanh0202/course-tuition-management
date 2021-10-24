package com.uit.coursemanagement.service.auth;

import com.uit.coursemanagement.constant.MessageCode;
import com.uit.coursemanagement.domain.user.User;
import com.uit.coursemanagement.dto.auth.UserPasswordDto;
import com.uit.coursemanagement.exception.InvalidException;
import com.uit.coursemanagement.exception.NotFoundException;
import com.uit.coursemanagement.repository.user.UserRepository;
import com.uit.coursemanagement.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final MessageHelper messageHelper;

    public Boolean changePassword(UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userPasswordDto.getUserId())
                .orElseThrow(()-> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (!b.matches(userPasswordDto.getOldPassword(), user.getPassword())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.WRONG_PASS));
        }
        String pwdBcrypt = BCrypt.hashpw(userPasswordDto.getNewPassword(), BCrypt.gensalt(10));
        user.setPassword(pwdBcrypt);
        userRepository.save(user);
        return true;
    }

}
