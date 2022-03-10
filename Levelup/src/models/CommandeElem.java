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
public class CommandeElem {
    int Quantite;
    int id_elem;
    String nomP;
    Double prix;
    String image;
    Spinner s = new Spinner();

    public CommandeElem() {
    }

    public CommandeElem(int id, int id_elem, String nomP, Double prix,String image) {
        this.Quantite = id;
        this.id_elem = id_elem;
        this.nomP = nomP;
        this.prix = prix;
        this.image = image;
        this.s = new Spinner();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50);
         valueFactory.setValue((int) id);
         this.s.setValueFactory(valueFactory);
       
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int id) {
        this.Quantite = id;
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
    
    

    @Override
    public String toString() {
        return "CommandeElem{" + "id=" + Quantite + ", id_elem=" + id_elem + ", nomP=" + nomP + ", prix=" + prix + '}';
    }
    
    
    
}
