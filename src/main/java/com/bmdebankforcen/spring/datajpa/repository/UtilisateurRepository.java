package com.bmdebankforcen.spring.datajpa.repository;

//package com.ebank.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmdebankforcen.spring.datajpa.model.Utilisateur;
import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByNomContaining(String nom);
}
