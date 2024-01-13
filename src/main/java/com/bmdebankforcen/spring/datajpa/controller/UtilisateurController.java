package com.bmdebankforcen.spring.datajpa.controller;

import com.bmdebankforcen.spring.datajpa.model.Utilisateur;
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
public class UtilisateurController {


    @Autowired
    UtilisateurRepository utilisateurRepository;

    // Ici, ajoutez les méthodes pour gérer les utilisateurs (GET, POST, PUT, DELETE)

 
    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs(@RequestParam(required = false) String nom) {
        try {
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

            if (nom == null)
                utilisateurRepository.findAll().forEach(utilisateurs::add);
            else
                utilisateurRepository.findByNomContaining(nom).forEach(utilisateurs::add);

            if (utilisateurs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 
    @GetMapping("/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable("id") long id) {
		Optional<Utilisateur> utilisateurData = utilisateurRepository.findById(id);

		if (utilisateurData.isPresent()) {
			return new ResponseEntity<>(utilisateurData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur _utilisateur = utilisateurRepository
                    .save(new Utilisateur(utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getMotDePasse())); // autres champs selon votre entité
            return new ResponseEntity<>(_utilisateur, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/utilisateurs/{id}")
	public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable("id") long id, @RequestBody Utilisateur utilisateur) {
		Optional<Utilisateur> utilisateurData = utilisateurRepository.findById(id);

		if (utilisateurData.isPresent()) {
			Utilisateur _utilisateur = utilisateurData.get();
			_utilisateur.setNom(utilisateur.getNom());
			_utilisateur.setEmail(utilisateur.getEmail());
			_utilisateur.setMotDePasse(utilisateur.getMotDePasse());
			return new ResponseEntity<>(utilisateurRepository.save(_utilisateur), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/utilisateurs/{id}")
	public ResponseEntity<HttpStatus> deleteUtilisateur(@PathVariable("id") long id) {
		try {
			utilisateurRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @DeleteMapping("/utilisateurs")
	public ResponseEntity<HttpStatus> deleteAllUtilisateurs() {
		try {
			utilisateurRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}

