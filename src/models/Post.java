/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import javafx.scene.control.TextField;

/**
 *
 * @author msi
 */
public class Post {

    int id;
    User id_user;
    String title;
    String content;
    Date datep;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(int id, String title, String content, Date datep, User id_user) {
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.content = content;
        this.datep = datep;
    }

    public Post(String title, String content, Date datep, User id_user) {
        this.id_user = id_user;
        this.title = title;
        this.content = content;
        this.datep = datep;
    }

    public Post(String text, String text0, User user, Date myDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Post(String title) {
        this.title = title;
    }

    public Post(int id) {
        this.id = id;
    }

    public Post(int id, String title, String content, Date datep) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datep = datep;
    }

   

   

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
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

    //to sTRING//

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", id_user=" + id_user + ", title=" + title + ", content=" + content + ", datep=" + datep + '}';
    }




public String concat(){
        return  title + ".@." +content+ ".@." ;
    }
   
   
}
