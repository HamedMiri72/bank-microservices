package com.hamedTech.loans.controller;

import com.hamedTech.loans.constants.LoansConstants;
import com.hamedTech.loans.dto.ErrorResponseDto;
import com.hamedTech.loans.dto.LoansContactInfoDto;
import com.hamedTech.loans.dto.LoansDto;
import com.hamedTech.loans.dto.ResponseDto;
import com.hamedTech.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name = "CRUD REST APIs for Loans in bank",
        description = "CRUD REST APIs in bank to CREATE, UPDATE, FETCH AND DELETE loan details")
public class LoansController {

    public static final Logger logger = LoggerFactory.getLogger(LoansController.class);
    private final ILoansService iLoansService;

    public LoansController(ILoansService iLoansService) {
        this.iLoansService = iLoansService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;

    @Operation(summary = "Create Loan REST API", description = "REST API to create Loan inside bank")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED"),
        @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber
    ) {

        iLoansService.createLoan(mobileNumber);


        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Loan REST API", description = "REST API to fetch Loan inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("bank-correlation-id") String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){

//        logger.debug("bank-correlation-id found: {}", correlationId);
        logger.debug("fetchLoanDetails method start");
        LoansDto lonaDto = iLoansService.fetchLoan(mobileNumber);

        logger.debug("fetchLoanDetails method end");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lonaDto);
    }

    @Operation(summary = "Update Loan REST API", description = "REST API to update Loan inside bank")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(
        @RequestBody @Valid LoansDto loansDto
    ){
        boolean isUpdated = iLoansService.updateLoan(loansDto);

        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417));
        }

    }

    @Operation(summary = "Delete Loan REST API", description = "REST API to delete Loan inside bank")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber
    ) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417));
        }
    }

    @Operation(summary = "Get Build Version REST API", description = "REST API to get Build Version inside bank")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
        @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/bulid-info")
    public ResponseEntity<String> getBuildVersion(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(summary = "Get Java Version REST API", description = "REST API to get Java Version inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME") + " " + environment.getProperty("MAVEN_HOME"));
    }

    @Operation(summary = "Get Contact Info REST API", description = "REST API to get Contact Info inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactDetails(){
        logger.debug("Invalid Loans contact-info API");
        throw new RuntimeException();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(loansContactInfoDto);
    }
}
