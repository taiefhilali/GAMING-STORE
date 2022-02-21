/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author msi
 */
public class Post {
    int id ; 
    int id_user;
    int idc;
    String title; 
    String content; 
    Date datep;

    public Post() {
    }

    public Post(int id, String title, String content, Date datep,int id_user,int idc) {
        this.id = id;
         this.idc = idc;
        this.id_user = id_user;
        this.title = title;
        this.content = content;
        this.datep = datep;
    }

    public Post(String title, String content, Date datep,int id_user,int idc) {
        this.id_user = id_user;
          this.idc = idc;
        this.title = title;
        this.content = content;
        this.datep = datep;
    }

    public Post(String gaming, String accesoires_et_jeux_videos, java.util.Date date) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatep() {
        return datep;
    }

    public void setDatep(Date datep) {
        this.datep = datep;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", id_user=" + id_user + ", idc=" + idc + ", title=" + title + ", content=" + content + ", datep=" + datep + '}';
    }

   
  
    
    
}
