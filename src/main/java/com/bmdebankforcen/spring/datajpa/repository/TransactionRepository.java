package com.bmdebankforcen.spring.datajpa.repository;

//package com.ebank.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmdebankforcen.spring.datajpa.model.Transaction;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDate(Date date);
}

