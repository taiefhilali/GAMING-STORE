/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author beldi
 */
public class WebController {

    static String key = null;

    public void printId(Object object) {
        key = (String) object;
    }

    public Boolean Verified() {
        URL url;
        try {
            url = new URL("https://www.google.com/recaptcha/api/siteverify?secret=6Lf-fLYeAAAAALntWUcfkc5ZOikC5IzZtrbtZLEA&response=" + key);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;

            while ((output = br.readLine()) != null) {
                if (br.readLine().toString().contains("true")) 
                {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
