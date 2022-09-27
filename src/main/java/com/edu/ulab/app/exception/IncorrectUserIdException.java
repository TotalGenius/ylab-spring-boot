package com.edu.ulab.app.exception;

public class IncorrectUserIdException extends RuntimeException{
    public IncorrectUserIdException(Long incorrectId){
        super("Incorrect user id:"+incorrectId);
    }
}
