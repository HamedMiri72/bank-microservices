package com.hamedTech.loans.repository;

import com.hamedTech.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<Loans, Long> {

}
