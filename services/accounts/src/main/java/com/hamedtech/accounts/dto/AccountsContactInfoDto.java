package com.hamedtech.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto{

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;


    // with record class java is going to make these feilds final and at the same time it is going to generate a getter an constructor behinde the scenes.
    // so there wont be setter methods
}
