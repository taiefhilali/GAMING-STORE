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
    private int idc; 
    private String contenu; 
    private String label;
    private int resp;
    
    
    
//constructors
    public Comment() {
    }

        public Comment(int idc, String contenu, String label, int resp) {
        this.idc = idc;
        this.contenu = contenu;
        this.label = label;
        this.resp = resp;
    }

    public Comment(String contenu, String label, int resp) {
        this.contenu = contenu;
        this.label = label;
        this.resp = resp;
    }
    
    
//getters&setters
    
    
   


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
        return "Comment{" + "idc=" + idc + ", contenu=" + contenu + ", label=" + label + ", resp=" + resp + '}';
    }
    
    
}
