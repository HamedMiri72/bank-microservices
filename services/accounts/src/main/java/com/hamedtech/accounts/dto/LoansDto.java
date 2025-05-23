package com.hamedtech.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "Loans",
        description = "Schema to hold loan details"
)
public class LoansDto {

    @NotEmpty(message = "Mobile number can not be null or empty")
    @Schema(
            description =   "Mobile number of the customer",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan number can not be null or empty")
    @Schema(
            description =   "Loan number of the customer",
            example = "123456789012"
    )
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Loan number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan type can not be null or empty")
    @Schema(
            description =   "Loan type of the customer",
            example = "Home Loan"
    )
    private String loanType;


    @Schema(
            description =   "Total loan of the customer",
            example = "100000"
    )
    @Positive(message = "Total loan must be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be greater than or equal to zero")
    @Schema(
            description =   "Amount paid by the customer",
            example = "10000"
    )
    private int amountPaid;


    @Schema(
            description =   "Outstanding amount of the customer",
            example = "10000"
    )
    @PositiveOrZero(message = "Outstanding amount must be greater than or equal to zero")
    private int outstandingAmount;
}
