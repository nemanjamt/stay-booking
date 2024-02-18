package com.staybooking.staybooking.exceptions;

public class PhoneNumberAlreadyUsedException extends RuntimeException{
    public PhoneNumberAlreadyUsedException(String message){
        super(message);
    }
}
