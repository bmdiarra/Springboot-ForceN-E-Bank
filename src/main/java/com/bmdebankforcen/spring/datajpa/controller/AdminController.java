package com.bmdebankforcen.spring.datajpa.controller;

//package com.ebank.spring.controller;

import com.bmdebankforcen.spring.datajpa.model.Admin;
import com.bmdebankforcen.spring.datajpa.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    // Méthodes pour gérer les admins
}

