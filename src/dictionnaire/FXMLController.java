/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionnaire;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class FXMLController implements Initializable {

    @FXML
    private TextArea txtDefinition;
    @FXML
    private TextField lblWord;
     HashMap<String,String> dictionary = new HashMap<>();
 void getDefinition(ActionEvent event) {
        
        txtDefinition.setWrapText(true);
        String wordToDefine;
        wordToDefine = lblWord.getText();
        txtDefinition.setText("");
        txtDefinition.appendText(dictionary.get(wordToDefine));
 }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         dictionary.put("a","a\n" +
            "ā,ə/Submit\n" +
            "determiner\n" +
            "1.\n" +
            "used when referring to someone or something for the first time in a text or conversation.\n" +
            "\"a man came out of the room\"\n" +
            "2.\n" +
            "used to indicate membership of a class of people or things.\n" +
            "\"he is a lawyer\"");

    dictionary.put("lol","az·i·muth  (ăz′ə-məth)\n" +
            "n.\n" +
            "1. The horizontal angular distance from a reference direction, usually the northern point of the horizon, to the point where a vertical circle through a celestial body intersects the horizon, usually measured clockwise. Sometimes the southern point is used as the reference direction, and the measurement is made clockwise through 360°.\n" +
            "2. The horizontal angle of an observer's bearing, measured clockwise from a reference direction such as true north.\n" +
            "3. The horizontal angle of a projectile's motion, measured relative to a reference direction such as true north.");

    dictionary.put("ba","ba1\n" +
            "NOUN\n" +
            "\n" +
            "In ancient Egypt, the supposed soul of a person or god, which survived after death but had to be sustained with offerings of food. It was typically represented as a human-headed bird.\n" +
            "See also ka");

    dictionary.put("bz","Bz\n" +
            "NOUN\n" +
            "\n" +
            "Chemistry \n" +
            "Benzene.\n" +
            "NOUN\n" +
            "\n" +
            "Chemistry \n" +
            "Benzoyl.\n" +
            "Origin\n" +
            "Late 19th century. Symbolic abbreviation for benzene<br>mid 19th century. Symbolic abbreviation for benzoyl.");

    dictionary.put("ca","ca1\n" +
            "\n" +
            "(preceding a date or amount) circa.\n" +
            "‘he was born ca 1400’");
    }    
    
}
