package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.reservation.request.ReservationCreate;
import com.staybooking.staybooking.dto.reservation.response.ReservationResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.model.others.Reservation;

import java.util.List;

public interface ReservationService {
    APIResponse<ReservationResponse> createReservation(ReservationCreate reservationToCreate) ;
    APIResponse<ReservationResponse> cancelReservation(Long id);
    APIResponse<ReservationResponse> getReservationApiResponse(Long id);
    Reservation findReservation(Long id);
    APIResponse<List<ReservationResponse>> findReservationsByAccommodation(Long accommodationId);
    APIResponse<Boolean> deleteReservation(Long id);
    Reservation createReservationFromDto(ReservationCreate reservationToCreate);
}
