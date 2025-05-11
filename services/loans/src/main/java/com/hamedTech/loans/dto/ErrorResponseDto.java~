package com.hamedTech.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error details"
)
public class ErrorResponseDto {

    @Schema(
            description = "API path where error occurred"
    )
    private String apiPath;

    @Schema(
            description = "Status code of the response"
    )
    private String statusCode;

    @Schema(
            description = "Error message"
    )
    private String errorMessage;

    @Schema(
            description = "Error time"
    )
    private LocalDateTime errorTime;

}
