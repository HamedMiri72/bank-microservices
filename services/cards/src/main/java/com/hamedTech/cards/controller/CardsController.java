package com.hamedTech.cards.controller;


import com.hamedTech.cards.constants.CardsConstants;
import com.hamedTech.cards.dto.CardDto;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "CRUD REST APIs for Cards in bank", description = "CRUD REST APIs for Cards in bank")
public class CardsController {

        private final ICardsService iCardsService;

        public CardsController(ICardsService iCardsService) {
                this.iCardsService = iCardsService;
        }
        @Value("${build.version}")
        private String buildVersion;


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

        @GetMapping("/fetch")
        @Operation(summary = "Fetch Card REST API", description = "REST API to fetch Card inside bank")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
                @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        public ResponseEntity<CardDto> fetchCard(
                @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber)
        {

            CardDto cardDto = iCardsService.fetchCard(mobileNumber);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cardDto);
        }

        @PutMapping("/update")
        @Operation(summary = "Update Card REST API", description = "REST API to update Card inside bank")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
                @ApiResponse(responseCode = "417", description = "HTTP Status 417 EXPECTATION FAILED"),
                @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        public ResponseEntity<ResponseDto> updateCard(@RequestBody CardDto cardDto){

            boolean isUpdated = iCardsService.updateCard(cardDto);

            if(isUpdated){
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
            }else{
                    return ResponseEntity
                            .status(HttpStatus.EXPECTATION_FAILED)
                            .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417));
            }
        }

        @DeleteMapping("/delete")
        @Operation(summary = "Delete Card REST API", description = "REST API to delete Card inside bank")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
                @ApiResponse(responseCode = "417", description = "HTTP Status 417 EXPECTATION FAILED"),
                @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        public ResponseEntity<ResponseDto> deleteCard(
                @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber)
        {
                boolean isDeleted = iCardsService.deleteCard(mobileNumber);

                if(isDeleted){
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
                }else{
                        return ResponseEntity
                                .status(HttpStatus.EXPECTATION_FAILED)
                                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417));
                }
        }


        @Operation(summary = "Get Build Version REST API", description = "REST API to get Build Version inside bank")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
                @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        ))
        })
        @GetMapping("/build-info")
        public ResponseEntity<String> getBuldVersion() {

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(buildVersion);
        }
}
