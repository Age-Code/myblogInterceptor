package org.example.myblog.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.myblog.domain.User;
import org.example.myblog.dto.UserDto;
import org.example.myblog.repository.UserRepository;
import org.example.myblog.service.UserService;
import org.example.myblog.util.TokenFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {

    final UserRepository userRepository;
    private final TokenFactory tokenFactory;

    // Signup
    @Override
    public UserDto.SignupResDto signup(UserDto.SignupReqDto signupReqDto){
        UserDto.SignupResDto res = userRepository.save(signupReqDto.toEntity()).toSignupResDto();

        return res;
    }

    // Login
    @Override
    public UserDto.LoginResDto login(UserDto.LoginReqDto loginReqDto){
        User user = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());

        if(user == null){
            // 로그인 실패
            return UserDto.LoginResDto.builder().refreshToken(null).build();
        }else{
            // 로그인 성공
            String refreshToken = tokenFactory.generateRefreshToken(user.getId());

            return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
        }
    }


}
