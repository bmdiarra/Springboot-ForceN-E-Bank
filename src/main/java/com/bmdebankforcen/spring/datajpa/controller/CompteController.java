package com.bmdebankforcen.spring.datajpa.controller;

import com.bmdebankforcen.spring.datajpa.dto.CompteDTO;

//package com.ebank.spring.controller;

import com.bmdebankforcen.spring.datajpa.model.Compte;
import com.bmdebankforcen.spring.datajpa.model.Tutorial;
import com.bmdebankforcen.spring.datajpa.model.Utilisateur;
import com.bmdebankforcen.spring.datajpa.repository.CompteRepository;
import com.bmdebankforcen.spring.datajpa.repository.UtilisateurRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CompteController {

    /*@Autowired
    CompteRepository compteRepository; */

    private final UtilisateurRepository utilisateurRepository;
    private final CompteRepository compteRepository;

    public CompteController(UtilisateurRepository utilisateurRepository, CompteRepository compteRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.compteRepository = compteRepository;
    }

    // Méthodes pour gérer les comptes

    @PostMapping("/comptes")
	public ResponseEntity<Compte> createCompte(@RequestBody CompteDTO compteDTO) {
		try {
            Utilisateur utilisateur = utilisateurRepository.findById(compteDTO.getUtilisateurId()).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
			Compte _compte = compteRepository
					.save(new Compte(compteDTO.getNumeroDeCompte(), compteDTO.getSolde(), utilisateur));
			return new ResponseEntity<>(_compte, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/comptes")
	public ResponseEntity<List<Compte>> getAllComptes(@RequestParam(required = false) String numeroDeCompte) {
		try {
			List<Compte> comptes = new ArrayList<Compte>();

			if (numeroDeCompte == null)
				compteRepository.findAll().forEach(comptes::add);
			else
				compteRepository.findByNumeroDeCompteContaining(numeroDeCompte).forEach(comptes::add);

			if (comptes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(comptes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/comptes/{id}")
	public ResponseEntity<Compte> getCompteById(@PathVariable("id") long id) {
		Optional<Compte> compteData = compteRepository.findById(id);

		if (compteData.isPresent()) {
			return new ResponseEntity<>(compteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/comptes/{id}")
	public ResponseEntity<Compte> updateCompte(@PathVariable("id") long id, @RequestBody Compte compte) {
		Optional<Compte> compteData = compteRepository.findById(id);

		if (compteData.isPresent()) {
			Compte _compte = compteData.get();
			_compte.setNumeroDeCompte(compte.getNumeroDeCompte());
			_compte.setSolde(compte.getSolde());
			return new ResponseEntity<>(compteRepository.save(_compte), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/comptes/{id}")
	public ResponseEntity<HttpStatus> deleteCompte(@PathVariable("id") long id) {
		try {
			compteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/comptes")
	public ResponseEntity<HttpStatus> deleteAllComptes() {
		try {
			compteRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
