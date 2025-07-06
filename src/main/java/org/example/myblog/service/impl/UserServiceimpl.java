package org.example.myblog.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.myblog.domain.User;
import org.example.myblog.dto.UserDto;
import org.example.myblog.repository.UserRepository;
import org.example.myblog.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {

    final UserRepository userRepository;

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
            throw new RuntimeException("Id or Password Error");
        }else{
            return UserDto.LoginResDto.builder().id(user.getId()).build();
        }
    }


}
