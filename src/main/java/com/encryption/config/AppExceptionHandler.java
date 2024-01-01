package com.encryption.config;

import com.encryption.exception.EncryptionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(Exception.class)
    public EncryptionException handleAllException(Exception e){
        return new EncryptionException("Something Went wrong");
    }
}
