package com.hamedtech.accounts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath; // which api try to invoke

    private HttpStatus errorCode; // what is the error code

    private String errorMessage; // what is the error message

    private LocalDateTime errorTime; // what time that error happened

}
