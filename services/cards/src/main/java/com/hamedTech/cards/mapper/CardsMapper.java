package com.hamedTech.cards.mapper;

import com.hamedTech.cards.dto.CardDto;
import com.hamedTech.cards.entity.Cards;

public class CardsMapper {


    public static CardDto mapToCardDto(Cards cards, CardDto cardDto) {

        cardDto.setCardNumber(cards.getCardNumber());
        cardDto.setCardType(cards.getCardType());
        cardDto.setMobileNumber(cards.getMobileNumber());
        cardDto.setTotalLimit(cards.getTotalLimit());
        cardDto.setAmountUsed(cards.getAmountUsed());
        cardDto.setAvailableAmount(cards.getAvailableAmount());

        return cardDto;

    }

    public static Cards mapToCards(CardDto cardDto, Cards cards) {

        cards.setCardNumber(cardDto.getCardNumber());
        cards.setCardType(cardDto.getCardType());
        cards.setMobileNumber(cardDto.getMobileNumber());
        cards.setTotalLimit(cardDto.getTotalLimit());
        cards.setAmountUsed(cardDto.getAmountUsed());
        cards.setAvailableAmount(cardDto.getAvailableAmount());

        return cards;
    }
}
