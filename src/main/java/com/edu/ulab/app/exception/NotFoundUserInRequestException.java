package com.edu.ulab.app.exception;

public class NotFoundUserInRequestException extends RuntimeException {
    public NotFoundUserInRequestException(){
        super("User data not found in request");
    }
}
