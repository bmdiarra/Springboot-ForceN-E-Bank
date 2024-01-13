package com.bmdebankforcen.spring.datajpa.model;

//package com.ebank.spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    // Constructeurs, getters, setters et toString


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", nom=" + nom + ", email=" + email + "]";
    }

    

}

