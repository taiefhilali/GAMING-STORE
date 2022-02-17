/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;



/**
 *
 * @author User
 */
public class Reclamation {
   
    
    //var
    private int id_reclamation;
    private int id_user;
    private int id_livraison;
    private String description;

        public Reclamation() {
        }

    public Reclamation(int id_reclamation, int id_user, int id_livraison, String description) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.id_livraison = id_livraison;
        this.description = description;
    }

    public Reclamation(int id_user, int id_livraison, String description) {
        this.id_user = id_user;
        this.id_livraison = id_livraison;
        this.description = description;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public String getDescription() {
        return description;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", id_livraison=" + id_livraison + ", description=" + description + '}';
    }
  
    
}
