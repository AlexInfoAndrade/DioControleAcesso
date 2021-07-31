package com.dio.live.exception.handler;

import com.dio.live.exception.DuplicateRegisterException;
import com.dio.live.exception.NotFoundRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DuplicateRegisterException.class)
    public ResponseEntity<Error> duplicateRegistrer(
            DuplicateRegisterException e , HttpServletRequest request) {

        Error err = new Error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NotFoundRegisterException.class)
    public ResponseEntity<Error> notFoundRegistrer(
            NotFoundRegisterException e , HttpServletRequest request) {

        Error err = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
