/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;

/**
 *
 * @author ASUS
 */
public class PaneEl {
    
    Label Lnom;
    Label LPrix;
    Label LQ;
    Label LNomR;
    Label LPrixR;
    Spinner s = new Spinner();
    int id;

    public PaneEl(String LNomR, Double LPrixR,int id) {
        this.Lnom.setText("Nom de Produit  :  ");
        this.LPrix.setText("Prix  :  ");
        this.LQ.setText("Quantité  :   ");
        this.LNomR.setText(LNomR);
        this.LPrixR.setText(LPrixR.toString());
        this.id = id;
      
    }

    public PaneEl(Label Lnom, Label LPrix, Label LQ, Label LNomR, Label LPrixR,Spinner s,int id) {
        this.Lnom = Lnom;
        this.LPrix = LPrix;
        this.LQ = LQ;
        this.LNomR = LNomR;
        this.LPrixR = LPrixR;
        this.s = s;
        this.id = id;
    }

    
    
    public PaneEl() {
    }

    
    
    
    
}
