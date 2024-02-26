package com.staybooking.staybooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T> {
    // The HTTP status code associated with the API response
    private Integer httpStatus;
    // A human-readable message providing additional information about the API response
    private String message;
    // An internal code aiding in error identification.
    private String internalCode;
    // The data payload included in the API response, holding the actual content.
    private T data;

    public static <T> APIResponse<T> generateApiResponse(T data, HttpStatus status, String internalCode, String message){
        return APIResponse.<T>builder()
                .httpStatus(status.value())
                .message(message)
                .internalCode(internalCode)
                .data(data)
                .build();
    }
}
