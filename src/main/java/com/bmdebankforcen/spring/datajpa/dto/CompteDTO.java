package com.bmdebankforcen.spring.datajpa.dto;


public class CompteDTO {
    private String numeroDeCompte;
    private Double solde;
    private Long utilisateurId;

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
    public Long getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    
}
