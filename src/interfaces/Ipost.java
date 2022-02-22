/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Post;

/**
 *
 * @author msi
 */
public interface Ipost {
     //ajouter
    public boolean ajouterPost(Post p);
     //lister
    public List<Post> afficherPost();
    //update
    
      public boolean modifierPost(Post p);
      //delete
          public boolean supprimerPost(Post p);
}
