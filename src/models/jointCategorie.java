/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Iskander
 */
public class jointCategorie {

    int idProd;
    String nomProd;
    String RefProd;
    String NomCat;
    double PrixProd;
    String descriptionProd;
    String emailUser;
    double PromotionProd;

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getNomCat() {
        return NomCat;
    }

    public void setNomCat(String NomCat) {
        this.NomCat = NomCat;
    }

    public double getPrixProd() {
        return PrixProd;
    }

    public void setPrixProd(double PrixProd) {
        this.PrixProd = PrixProd;
    }

    public String getDescriptionProd() {
        return descriptionProd;
    }

    public void setDescriptionProd(String descriptionProd) {
        this.descriptionProd = descriptionProd;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public double getPromotionProd() {
        return PromotionProd;
    }

    public void setPromotionProd(double PromotionProd) {
        this.PromotionProd = PromotionProd;

    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getRefProd() {
        return RefProd;
    }

    public void setRefProd(String RefProd) {
        this.RefProd = RefProd;
    }

    public jointCategorie() {
    }

    public jointCategorie(String nomProd, String RefProd, String NomCat, double PrixProd, String descriptionProd, String emailUser, double PromotionProd, int idProd) {
        this.nomProd = nomProd;
        this.RefProd = RefProd;
        this.NomCat = NomCat;
        this.PrixProd = PrixProd;
        this.descriptionProd = descriptionProd;
        this.emailUser = emailUser;
        this.PromotionProd = PromotionProd;
        this.idProd = idProd;
    }

}
