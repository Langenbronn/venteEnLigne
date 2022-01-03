package com.venteEnLigne.venteEnLigne.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {

    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timesstamp;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timesstamp) {
        this.message = message;
//        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timesstamp = timesstamp;
    }

    public String getMessage() {
        return message;
    }

//    public Throwable getThrowable() {
//        return throwable;
//    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimesstamp() {
        return timesstamp;
    }
}
