package com.hamedTech.cards.controller;


import com.hamedTech.cards.constants.CardsConstants;
import com.hamedTech.cards.dto.ErrorResponseDto;
import com.hamedTech.cards.dto.ResponseDto;
import com.hamedTech.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Tag(name = "CRUD REST APIs for Cards in bank", description = "CRUD REST APIs for Cards in bank")
public class CardsController {

        private final ICardsService iCardsService;
        private ICardsService cardsService;


        @PostMapping("/create")
        @Operation(summary = "Create Card REST API", description = "REST API to create Card inside bank")
        @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        public ResponseEntity<ResponseDto> createCard(
                @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber
        ){
                iCardsService.createCard(mobileNumber);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
}
