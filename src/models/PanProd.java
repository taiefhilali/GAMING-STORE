/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 *
 * @author ASUS
 */
public class PanProd {
    int id_elem;
    int idPr;
    String nomP;
    Double prix;
    String image;
    int quantite;
    Spinner s = new Spinner();

    public PanProd(int id_elem,int idp ,String nomP, Double prix,String image,int quantite) {
        this.id_elem = id_elem;
        this.idPr = idPr;
        this.nomP = nomP;
        this.prix = prix;
        this.image = image;
        this.quantite=quantite;
        this.s = new Spinner();
         SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
         valueFactory.setValue(1);
         s.setValueFactory(valueFactory);
    }

    public PanProd() {
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public int getId_elem() {
        return id_elem;
    }

    public void setId_elem(int id_elem) {
        this.id_elem = id_elem;
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

    public Spinner getS() {
        return s;
    }

    public void setS(Spinner s) {
        this.s = s;
    }

    public int getIdPr() {
        return idPr;
    }

    public void setIdPr(int idPr) {
        this.idPr = idPr;
    }

    
    @Override
    public String toString() {
        return "PanProd{" + "id_elem=" + id_elem + ", nomP=" + nomP + ", prix=" + prix + '}';
    }
    
    
    
}
