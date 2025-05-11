package com.hamedTech.loans.service.impl;

import com.hamedTech.loans.constants.LoansConstants;
import com.hamedTech.loans.dto.LoansDto;
import com.hamedTech.loans.entity.Loans;
import com.hamedTech.loans.exception.LoanAlreadyExistsException;
import com.hamedTech.loans.exception.ResourceNotFoundException;
import com.hamedTech.loans.mapper.LoansMapper;
import com.hamedTech.loans.repository.LoansRepository;
import com.hamedTech.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;


    /**
     *
     * @param mobileNumber - mobile number of the customer
     */
    @Override
    public void createLoan(String mobileNumber) {

        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);

        if(loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with given mobileNumber"+ mobileNumber);
        }
        loansRepository.save(createNewLoans(mobileNumber));

    }

    private Loans createNewLoans(String mobileNumber) {

        Loans newloans = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newloans.setLoanNumber(String.valueOf(randomLoanNumber));
        newloans.setLoanType(LoansConstants.HOME_LOAN);
        newloans.setMobileNumber(mobileNumber);
        newloans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newloans.setAmountPaid(0);
        newloans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        newloans.setCreatedAt(LocalDateTime.now());
        newloans.setCreatedBy("Admin");
        return newloans;

    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "MobileNumber", mobileNumber));

        return LoansMapper.mapToLoansDto(loans, new LoansDto());


    }

    /**
     *
     * @param loansDto - loan details
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoansDto loansDto) {
        boolean isUpdated = false;

        Loans loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("loan", "MobileNumber", loansDto.getMobileNumber()));

        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        isUpdated = true;
        return isUpdated;
    }

    /**
     *
     * @param mobileNumber - mobile number of the customer
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted = false;
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "MobileNumber", mobileNumber));

        loansRepository.delete(loans);
        isDeleted = true;
        return isDeleted;
    }
}
