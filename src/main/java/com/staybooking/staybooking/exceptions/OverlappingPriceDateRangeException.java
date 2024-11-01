package com.staybooking.staybooking.exceptions;

public class OverlappingPriceDateRangeException extends RuntimeException{
    public OverlappingPriceDateRangeException(String message){
        super(message);
    }
}
