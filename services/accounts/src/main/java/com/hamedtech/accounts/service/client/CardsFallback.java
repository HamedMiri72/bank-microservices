package com.hamedtech.accounts.service.client;

import com.hamedtech.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}
