package com.bmdebankforcen.spring.datajpa.model;

//package com.ebank.spring.model;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    @JsonBackReference
    private Compte compte;

    public Transaction() {

    }


    public Transaction(Double montant, String type, Compte compte) {
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", montant=" + montant + ", date=" + date + ", type=" + type + ", compte="
                + compte + "]";
    }

    // Constructeurs, getters, setters et toString

    
}

