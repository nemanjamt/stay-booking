package com.staybooking.staybooking.exceptions;

public class EmailAlreadyUsedException extends RuntimeException{
    public EmailAlreadyUsedException(String message){
        super(message);
    }
}
