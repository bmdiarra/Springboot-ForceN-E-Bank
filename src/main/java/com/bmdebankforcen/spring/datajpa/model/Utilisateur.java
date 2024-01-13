package com.bmdebankforcen.spring.datajpa.model;

//package com.ebank.spring.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "motDePasse")
    private String motDePasse;

    @OneToMany(mappedBy = "utilisateur")
    private List<Compte> comptes;

    public Utilisateur() {

	}

    public Utilisateur( String nom, String email, String motDePasse) {
        
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", email=" + email + ", motDePasse=" + motDePasse
                + ", comptes=" + comptes + "]";
    }

    // Constructeurs, getters, setters et toString

    
}

