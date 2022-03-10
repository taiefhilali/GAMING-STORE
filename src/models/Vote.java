/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author msi
 */
public class Vote {
    int idv;
    Post id;
    Post id_user;
    int vote_type;
  

    public Vote(int idv, Post id, Post id_user, int vote_type) {
        this.idv = idv;
        this.id = id;
        this.id_user = id_user;
        this.vote_type = vote_type;
        
    }

    public Vote(Post id, Post id_user, int vote_type) {
        this.id = id;
        this.id_user = id_user;
        this.vote_type = vote_type;
       
    }

    public Vote() {
    }

    public Vote(int vote_type) {
        this.vote_type = vote_type;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int idv) {
        this.idv = idv;
    }

    public Post getId() {
        return id;
    }

    public void setId(Post id) {
        this.id = id;
    }

    public Post getId_user() {
        return id_user;
    }

    public void setId_user(Post id_user) {
        this.id_user = id_user;
    }

    public int getVote_type() {
        return vote_type;
    }

    public void setVote_type(int vote_type) {
        this.vote_type = vote_type;
    }

    @Override
    public String toString() {
        return "Vote{" + "idv=" + idv + ", id=" + id + ", id_user=" + id_user + ", vote_type=" + vote_type + '}';
    }

   
   
    
    
    
}
