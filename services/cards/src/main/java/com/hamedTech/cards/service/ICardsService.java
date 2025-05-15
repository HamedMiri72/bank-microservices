package com.hamedTech.cards.service;

import com.hamedTech.cards.dto.CardDto;

public interface ICardsService {

    /**
     * Creates a card associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number to associate with the new card
     * @return null, as this method does not produce a result
     */
    void createCard(String mobileNumber);

    /**
     * Fetches the card details associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number associated with the card
     * @return the card details
     */
    CardDto fetchCard(String mobileNumber);

    /**
     * Updates the card details associated with the provided mobile number.
     *
     * @param cardDto the card details to update
     * @return true if the update was successful, false otherwise
     */
    boolean updateCard(CardDto cardDto);

    /**
     * Deletes the card associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number associated with the card
     * @return true if the deletion was successful, false otherwise
     */
    boolean deleteCard(String mobileNumber);
}
