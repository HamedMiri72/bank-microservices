package com.hamedTech.message.dto;

/**
 *
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */
public record AccountsMsgDto(
        long accountNumber,
        String name,
        String email,
        String mobileNumber
) {
}
