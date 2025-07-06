package org.example.myblog.dto;

import lombok.*;
import org.example.myblog.domain.User;

public class UserDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
        String username;
        String password;
        String name;

        public User toEntity() { return User.of(getUsername(), getPassword(), getName()); }
    }

    // Signup Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupResDto {
        Long id;
    }

    // Login Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        String username;
        String password;
    }

    // Login Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginResDto {
        String refreshToken;
    }
}
