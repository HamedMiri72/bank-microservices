package com.hamedTech.cards.service.impl;

import com.hamedTech.cards.dto.CardDto;
import com.hamedTech.cards.repository.CardsRepository;
import com.hamedTech.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository repository;

    @Override
    public Void createCard(String mobileNumber) {
        return null;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }
}
