package com.staybooking.staybooking.exceptions;

public class OverlappingReservationDateRangeException extends RuntimeException {
    public OverlappingReservationDateRangeException(String message) {
        super(message);
    }
}
