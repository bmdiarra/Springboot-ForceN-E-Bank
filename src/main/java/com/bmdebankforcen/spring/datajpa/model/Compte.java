package com.bmdebankforcen.spring.datajpa.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comptes")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "numeroDeCompte")
    private String numeroDeCompte;

    @Column(name = "solde")
    private Double solde;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;

    public Compte() {

	}

    public Compte(String numeroDeCompte, Double solde, Utilisateur utilisateur) {
        this.numeroDeCompte = numeroDeCompte;
        this.solde = solde;
        this.utilisateur = utilisateur;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public void setNumeroDeCompte(String numeroDeCompte) {
        this.numeroDeCompte = numeroDeCompte;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Compte [id=" + id + ", numeroDeCompte=" + numeroDeCompte + ", solde=" + solde + ", utilisateur="
                + utilisateur + ", transactions=" + transactions + "]";
    }

    // Constructeurs, getters, setters et toString
}

