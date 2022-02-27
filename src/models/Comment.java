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
//attributs


public class Comment {

    public static Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int idc; 
    private User id_user;
    private String contenu; 
    private String label;
    private int resp;
    private Post id_post;
    int nblike;
    int nbdislike;
    
    
//constructors
    public Comment() {
    }

        public Comment(int idc, String contenu, String label, int resp,User id_user,Post id_post,int nblike, int nbdislike) {
            System.out.println("post_id"+id_post);
            this.nblike = nblike;
          this.nbdislike = nbdislike;
        this.idc = idc;
        this.id_user = id_user;
        this.contenu = contenu;
        this.label = label;
        this.resp = resp;
        this.id_post=id_post;
    }

    public Comment(String contenu, String label, int resp,User id_user,Post id_post,int nblike, int nbdislike) {
        this.contenu = contenu;
        this.label = label;
        this.nbdislike = nbdislike;
          this.nblike = nblike;
        this.resp = resp;
        this.id_user = id_user;
        this.id_post=id_post;
        
    }
    
    
//getters&setters
 public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }

    public int getNbdislike() {
        return nbdislike;
    }

    public void setNbdislike(int nbdislike) {
        this.nbdislike = nbdislike;
    }
    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

   

    public Post getId_post() {
        System.out.println("id_post"+id_post);
        
        return this.id_post;
        
    }

    public void setId_post(Post id_post) {
        this.id_post = id_post;
    }
    
    
   


    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }


    //tostring

    @Override
    public String toString() {
        return "Comment{" + "idc=" + idc + ", id_user=" + id_user + ", contenu=" + contenu + ", label=" + label + ", resp=" + resp + ", id_post=" + id_post + ", nblike=" + nblike + ", nbdislike=" + nbdislike + '}';
    }

  

    

   
    
    
}
