package com.bmdebankforcen.spring.datajpa.repository;

//package com.ebank.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmdebankforcen.spring.datajpa.model.Compte;
import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByNumeroDeCompteContaining(String numeroDeCompte);
}

