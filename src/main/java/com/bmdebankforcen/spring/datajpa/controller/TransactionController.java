package com.bmdebankforcen.spring.datajpa.controller;

import com.bmdebankforcen.spring.datajpa.dto.TransactionDTO;
import com.bmdebankforcen.spring.datajpa.model.Transaction;

//package com.ebank.spring.controller;

import com.bmdebankforcen.spring.datajpa.model.Transaction;
import com.bmdebankforcen.spring.datajpa.model.Compte;
import com.bmdebankforcen.spring.datajpa.repository.TransactionRepository;
import com.bmdebankforcen.spring.datajpa.repository.TransactionRepository;
import com.bmdebankforcen.spring.datajpa.repository.CompteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TransactionController {

    /*@Autowired
    TransactionRepository transactionRepository; */

    // Méthodes pour gérer les transactions

    private final CompteRepository compteRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(CompteRepository compteRepository, TransactionRepository transactionRepository) {
        this.compteRepository = compteRepository;
        this.transactionRepository = transactionRepository;
    }

    // Méthodes pour gérer les transactions

    @PostMapping("/transactions")
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
		try {
            Compte compte = compteRepository.findById(transactionDTO.getCompteId()).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
			Transaction _transaction = transactionRepository
					.save(new Transaction(transactionDTO.getMontant(), transactionDTO.getType(), compte));
			return new ResponseEntity<>(_transaction, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam(required = false) Date date) {
		try {
			List<Transaction> transactions = new ArrayList<Transaction>();

			if (date == null)
				transactionRepository.findAll().forEach(transactions::add);
			else
				transactionRepository.findByDate(date).forEach(transactions::add);

			if (transactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
		Optional<Transaction> transactionData = transactionRepository.findById(id);

		if (transactionData.isPresent()) {
			return new ResponseEntity<>(transactionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/transactions/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
		Optional<Transaction> transactionData = transactionRepository.findById(id);

		if (transactionData.isPresent()) {
			Transaction _transaction = transactionData.get();
			_transaction.setMontant(transaction.getMontant());
			_transaction.setType(transaction.getType());
			return new ResponseEntity<>(transactionRepository.save(_transaction), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id) {
		try {
			transactionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/transactions")
	public ResponseEntity<HttpStatus> deleteAllTransactions() {
		try {
			transactionRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}

