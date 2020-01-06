package com.tt.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtValidateException extends Exception {
    public JwtValidateException(String message) {
        super(message);
    }
}