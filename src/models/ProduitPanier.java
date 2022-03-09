/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.control.Spinner;

/**
 *
 * @author ASUS
 */
public class ProduitPanier {
    int id_elem;
    int idPr;
    String nomP;
    Double prix;
    Integer Quantite;

    public ProduitPanier() {
    }

    public ProduitPanier(int id_elem, int idPr, String nomP, Double prix, Integer Quantite) {
        this.id_elem = id_elem;
        this.idPr = idPr;
        this.nomP = nomP;
        this.prix = prix;
        this.Quantite = Quantite;
    }

    public int getId_elem() {
        return id_elem;
    }

    public void setId_elem(int id_elem) {
        this.id_elem = id_elem;
    }

    public int getIdPr() {
        return idPr;
    }

    public void setIdPr(int idPr) {
        this.idPr = idPr;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return Quantite;
    }

    public void setQuantite(Integer Quantite) {
        this.Quantite = Quantite;
    }

    @Override
    public String toString() {
        return "ProduitPanier{" + "id_elem=" + id_elem + ", idPr=" + idPr + ", nomP=" + nomP + ", prix=" + prix + ", Quantite=" + Quantite + '}';
    }
    
    
}
