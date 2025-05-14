package com.hamedTech.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Schema(
        name = "card",
        description = "Schema to hold card details"
)
@Validated
public class CardDto {

    @NotEmpty(message = "mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(
            description = "mobile Number of the customer",
            example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message = "card number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})",message = "Card number must be 12 digits")
    @Schema(
            description = "card Number of the customer",
            example = "123456789012"
    )
    private String cardNumber;

    @NotEmpty(message = "card type can not be null or empty")
    @Schema(
            description = "card type of the customer",
            example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "total limit must be greater than zero")
    @Schema(
            description = "total limit of the customer",
            example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "amount used must be greater than or equal to zero")
    @Schema(
            description = "amount used of the customer",
            example = "50000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "available amount must be greater than or equal to zero")
    @Schema(
            description = "available amount of the customer",
            example = "50000"
    )
    private int availableAmount;
}

