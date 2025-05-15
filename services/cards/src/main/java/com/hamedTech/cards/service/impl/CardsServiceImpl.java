package com.hamedTech.cards.service.impl;

import com.hamedTech.cards.constants.CardsConstants;
import com.hamedTech.cards.dto.CardDto;
import com.hamedTech.cards.entity.Cards;
import com.hamedTech.cards.exception.CardAlreadyExistsException;
import com.hamedTech.cards.exception.ResourceNotFoundException;
import com.hamedTech.cards.mapper.CardsMapper;
import com.hamedTech.cards.repository.CardsRepository;
import com.hamedTech.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);

        if (cards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();

        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setMobileNumber(mobileNumber);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber));


        return CardsMapper.mapToCardDto(cards, new CardDto());


    }

    /**
     * Updates the details of an existing card based on the information provided in the given CardDto.
     * If a card associated with the provided mobile number does not exist, a ResourceNotFoundException is thrown.
     *
     * @param cardDto the data transfer object containing the updated card details,
     *                such as card number, card type, total limit, amount used, and available amount.
     * @return true if the card details were successfully updated; false otherwise.
     * @throws ResourceNotFoundException if no card is found associated with the mobile number provided in the cardDto.
     */
    @Override
    public boolean updateCard(CardDto cardDto) {
        boolean isUpdated = false;

        Cards cards = cardsRepository.findByMobileNumber(cardDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "MobileNumber", cardDto.getMobileNumber()));

        CardsMapper.mapToCards(cardDto, cards);
        cardsRepository.save(cards);
        isUpdated = true;
        return isUpdated;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean isDeleted = false;

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber));

        cardsRepository.delete(cards);
        isDeleted = true;
        return isDeleted;
    }
}
