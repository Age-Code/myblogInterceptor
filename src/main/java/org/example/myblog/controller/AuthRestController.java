package org.example.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.myblog.util.TokenFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    final TokenFactory tokenFactory;
    private final String prefix = "Bearer ";

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String accessToken = null;
        String refreshToken = request.getHeader("RefreshToken");

        if(refreshToken.startsWith(prefix)) {
            refreshToken = refreshToken.substring(prefix.length());

            accessToken = prefix + tokenFactory.generateAccessToken(refreshToken);
        }

        return ResponseEntity.status(HttpStatus.OK).header("Authorization", accessToken).build();
    }
}
