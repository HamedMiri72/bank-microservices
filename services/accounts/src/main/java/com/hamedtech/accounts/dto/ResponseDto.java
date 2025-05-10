package com.hamedtech.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold the response details"
)
public class ResponseDto {

    @Schema(
            description = "Status code of the response"

    )
    private String statusCode;

    @Schema(
            description = "Status code of the response"
    )
    private String statusMsg;
}
