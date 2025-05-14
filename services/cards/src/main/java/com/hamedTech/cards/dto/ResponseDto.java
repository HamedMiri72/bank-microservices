package com.hamedTech.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(
    name = "ResponseDto",
    description = "schema to hold the response details"
)
public class ResponseDto {

    @Schema(
        description = "status code of the response"
    )
    private String statusCode;

    @Schema(
            description = "status message of the response"
    )
    private String statusMsg;
}
