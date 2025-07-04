package com.hamedtech.accounts.controller;


import com.hamedtech.accounts.constants.AccountsConstants;
import com.hamedtech.accounts.dto.AccountsContactInfoDto;
import com.hamedtech.accounts.dto.CustomerDto;
import com.hamedtech.accounts.dto.ErrorResponseDto;
import com.hamedtech.accounts.dto.ResponseDto;
import com.hamedtech.accounts.service.IAccountsService;
import io.github.resilience4j.retry.annotation.Retry;
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
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD REST APIs for Accounts in bank", description = "CRUD REST APIs for Accounts in bank")
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);


    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @Value("${build.version}")
    private String buildVersion;


    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;



    @Operation(summary = "Create Account REST API", description = "REST API to create Customer and Account inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(
            @RequestBody @Valid CustomerDto customerDto
    ) {

        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @Operation(summary = "Fetch Account REST API", description = "REST API to fetch Customer and Account inside bank")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccontDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber
    ){

        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update Account REST API", description = "REST API to update Customer and Account inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "417", description = "HTTP Status 417 EXPECTATION FAILED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(
            @RequestBody @ Valid CustomerDto customerDto
    ){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417));
        }
    }

    @Operation(summary = "Delete Account REST API", description = "REST API to delete Customer and Account inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "417", description = "HTTP Status 417 EXPECTATION FAILED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class) ))

    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccounts(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber
    ){

        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417));
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
    @Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        logger.debug("Invalid build-info API");
        throw new NullPointerException();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable){
        logger.debug("getBuildInfoFallback method Invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("0.9");
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

    @Operation(summary = "Get Contact Info", description = "Contact info deteaild that can be reached out in case of any issues")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status 500 INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ))
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }


}
