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
    private String status;
    private Integer httpStatus;
    private String message;
    private String internalCode;
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
