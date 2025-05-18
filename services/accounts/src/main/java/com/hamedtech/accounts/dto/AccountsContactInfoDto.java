package com.hamedtech.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(
        String message,
        Map<String, String> contactDetails,
        List<String> onCallSupport


        // with record class java is going to make these feilds final and at the same time it is going to generate a getter an constructor behinde the scenes.
        // so there wont be setter methods
) {
}
