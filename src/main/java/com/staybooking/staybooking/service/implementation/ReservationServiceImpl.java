package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.reservation.request.ReservationCreate;
import com.staybooking.staybooking.dto.reservation.response.ReservationResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.exceptions.OverlappingReservationDateRangeException;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Reservation;
import com.staybooking.staybooking.model.users.Renter;
import com.staybooking.staybooking.repository.ReservationRepository;
import com.staybooking.staybooking.service.AccommodationService;
import com.staybooking.staybooking.service.RenterService;
import com.staybooking.staybooking.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    private final ModelMapper modelMapper;
    private final AccommodationService accommodationService;
    private final RenterService renterService;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper, RenterService renterService, AccommodationService accommodationService){
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.accommodationService = accommodationService;
        this.renterService = renterService;
    }

    private ReservationResponse mapReservationToResponse(Reservation reservation){
        PropertyMap<Reservation, ReservationResponse> propertyMap = new PropertyMap<Reservation, ReservationResponse>() {
            @Override
            protected void configure() {
                map(source.getAccommodation().getId(), destination.getAccommodationId());
                map(source.getRenter().getId(), destination.getRenterId());
            }
        };

        return modelMapper.map(reservation, ReservationResponse.class);
    }

    @Override
    public APIResponse<ReservationResponse> createReservation(ReservationCreate reservationToCreate)  {
        if(reservationRepository.existsOverlappingReservation(reservationToCreate.getAccommodationId(), reservationToCreate.getStartDate(), reservationToCreate.getEndDate())){
            throw new OverlappingReservationDateRangeException("The reservation range overlaps with an existing reservation for the accommodation");
        }
        Reservation reservation = createReservationFromDto(reservationToCreate);
        ReservationResponse reservationResponse = mapReservationToResponse(reservationRepository.save(reservation));
        return APIResponse.generateApiResponse(reservationResponse, HttpStatus.CREATED, "2001","Reservation successful created");
    }

    @Override
    public APIResponse<ReservationResponse> cancelReservation(Long id) {
        Reservation reservation = findReservation(id);
        reservation.setCanceled(true);
        ReservationResponse reservationResponse = mapReservationToResponse(reservationRepository.save(reservation));
        return APIResponse.generateApiResponse(reservationResponse, HttpStatus.OK, "2000","Reservation successful canceled");
    }


    @Override
    public APIResponse<ReservationResponse> getReservationApiResponse(Long id) {
        ReservationResponse reservationResponse = mapReservationToResponse(findReservation(id));
        return APIResponse.generateApiResponse(reservationResponse, HttpStatus.OK, "2000","Reservation successful found");
    }

    @Override
    public Reservation findReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.RESERVATION)));
    }

    @Override
    public APIResponse<List<ReservationResponse>> findReservationsByAccommodation(Long accommodationId) {
        List<Reservation> reservations = reservationRepository.findReservationsByAccommodationId(accommodationId);
        List<ReservationResponse> reservationsResponse = reservations.stream().map(this::mapReservationToResponse).toList();
        return APIResponse.generateApiResponse(reservationsResponse, HttpStatus.OK, "2000","Reservations successful found");
    }

    @Override
    public APIResponse<Boolean> deleteReservation(Long id) {
        Reservation reservation = findReservation(id);
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
        return APIResponse.generateApiResponse(true, HttpStatus.OK, "2000","Reservation successful deleted");
    }

    @Override
    public Reservation createReservationFromDto(ReservationCreate reservationToCreate) {
        Accommodation accommodation = accommodationService.findAccommodation(reservationToCreate.getAccommodationId());
        Renter renter = renterService.findRenter(reservationToCreate.getRenterId());
        Reservation reservation = new Reservation();
        reservation.setCreatedDate(LocalDate.now());
        reservation.setStartDate(reservationToCreate.getStartDate());
        reservation.setEndDate(reservationToCreate.getEndDate());
        reservation.setAccommodation(accommodation);
        reservation.setRenter(renter);
        return reservation;
    }
}
