package com.dio.live.exception;

public class DuplicateRegisterException extends RuntimeException {

    public DuplicateRegisterException(String msg) {
        super(msg);
    }
}
