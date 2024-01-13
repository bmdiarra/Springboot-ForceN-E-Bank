package com.bmdebankforcen.spring.datajpa.controller;


import com.bmdebankforcen.spring.datajpa.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    // Méthodes pour gérer les admins
}

