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
    int Upvote;
    int Downvote;

    public Vote(int idv, Post id, Post id_user, int Upvote, int Downvote) {
        this.idv = idv;
        this.id = id;
        this.id_user = id_user;
        this.Upvote = Upvote;
        this.Downvote = Downvote;
    }

    public Vote(Post id, Post id_user, int Upvote, int Downvote) {
        this.id = id;
        this.id_user = id_user;
        this.Upvote = Upvote;
        this.Downvote = Downvote;
    }

    public Vote() {
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

    public int getUpvote() {
        return Upvote;
    }

    public void setUpvote(int Upvote) {
        this.Upvote = Upvote;
    }

    public int getDownvote() {
        return Downvote;
    }

    public void setDownvote(int Downvote) {
        this.Downvote = Downvote;
    }

    @Override
    public String toString() {
        return "Vote{" + "idv=" + idv + ", id=" + id + ", id_user=" + id_user + ", Upvote=" + Upvote + ", Downvote=" + Downvote + '}';
    }
    
    
    
}
