package com.bmdebankforcen.spring.datajpa.repository;

//package com.ebank.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bmdebankforcen.spring.datajpa.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Méthodes spécifiques pour Admin si nécessaire
}

