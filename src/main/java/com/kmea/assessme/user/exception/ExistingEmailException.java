package com.kmea.assessme.user.exception;

public class ExistingEmailException extends Exception{
    public ExistingEmailException(String message){
        super(message);
    }
}
