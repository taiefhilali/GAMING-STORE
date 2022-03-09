/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badword;

import static badword.BadWordFilter.censor;

/**
 *
 * @author msi
 */
public class Badword {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws InterruptedException {
      
//		String input="how to kill";
//		String output = BadWordFilter.getCensoredText(input);
//		System.out.println(output);
String extract = "shit is a shit "+
					"portal for geeks. I am pursuing my " +
					"major in computer science. ";
	String cen = "shit";
	System.out.println(censor(extract, cen));
	

    }
    
}
