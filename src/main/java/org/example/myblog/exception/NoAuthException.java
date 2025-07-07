package org.example.myblog.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
@NoArgsConstructor
public class NoAuthException extends RuntimeException {
    public NoAuthException(String message) {
        super(message);
    }
}
