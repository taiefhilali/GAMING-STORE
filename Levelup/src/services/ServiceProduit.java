package services;

import interfaces.Iproduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Categorie;
import models.User;
import models.Produit;
import models.jointCategorie;
import utils.MaConnexion;

/**
 *
 * @author Iskander
 */
public class ServiceProduit implements Iproduit {

    // Variable 1 : Variable de connexion 
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    // Crud d'ajouter d'un produit selon un produit saisie {{ WITHOUT USER ID ]] 

    public String ajouterProduit(Produit p) {
        String s = "";
        String request = "INSERT INTO `produit`(`nom`, `reference`, `id_categorie`, `prix`, `description`,`id_user`,`promotion`,`image`) "
                + "VALUES ('" + p.getNom() + "','"
                + p.getReference() + "','"
                + p.getCategorie().getId_categorie() + "','"
                + p.getPrix() + "','"
                + p.getDescription() + "','"
                + p.getUser().getId() + "','"
                + p.getPromotion() + "','"
                + p.getImage() + "')";
        try {
            // Définier une var Statement responsable de la connexion
            Statement st = cnx.createStatement();
            //Execution da la fonction statement
            //execute update pour l'éxecution même au cours du modification i lfait maj
            st.executeUpdate(request);
            System.out.println("  ");
            s = " ** L'ajout du produit est établie avec succés! **";
            return s;
        } catch (SQLException ex) {
            s = "Echec d'ajout! Veuillez saisir une réference unique";
            return s;
        }

    }

    // Crud: Modification d'un produit existant selon son objet p et non l'id
    @Override
    public void modifierProduit(Produit p) {
        String s = "";
        String request = "UPDATE `produit` SET `nom`='" + p.getNom()
                + "',`reference`='" + p.getReference()
                + "',`id_categorie`=" + p.getCategorie().getId_categorie()
                + ",`prix`=" + p.getPrix()
                + ",`description`='" + p.getDescription()
                + "',`id_user`=" + p.getUser().getId()
                + ",`promotion`=" + p.getPromotion()
                //                + ",`image`='" + s + 
                + " WHERE id_produit =" + p.getId_produit() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" ** Produit modifié avec succés ** ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Crud suppression selon l'appel d'un id d'un produit
    @Override
    public void supprimerProduit(Produit p) {
        String request = "DELETE FROM `produit` WHERE `id_produit` = " + p.getId_produit() + " ";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(request);
            System.out.println(" ** Produit supprimé avec succés ** ");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(" Erreur de suppression ");
        }
    }

    // Affichage de tout les produits du magasin sans exception 
    @Override
    public List<Produit> afficherProduit() {
        List<Produit> Produits = new ArrayList<Produit>();
        String query = "SELECT * FROM produit p inner join categorie c on p.id_categorie  = c.id_categorie join user u on p.id_user = u.id_user";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                Produits.add(new Produit(rs.getInt("id_produit"),
                        rs.getString(2),
                        rs.getString(3),
                        new Categorie(rs.getInt("c.id_categorie"),
                                rs.getString("c.nom_categorie")),
                        rs.getDouble(5),
                        rs.getString(6),
                        new User(rs.getString("email")),
                        rs.getDouble(8),
                        rs.getString(9)
                //,rs.getString("email"),rs.getString("password"),rs.getString("role"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("tel"),rs.getDate("dns"))
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Produits;
//    }    // Exemple métier : Top 10 des produits selon chaque categories ( selon table commande) 
    }

    // Fonction de tri des produits par prix croissants ( du prix le plus faible vers le prix le plus elevée ) 
    @Override
    public void TrierProduitParPrix() {
        List<Produit> Produits = new ArrayList<Produit>();
        String query = "SELECT * FROM produit p inner join categorie c on p.id_categorie  = c.id_categorie join user u on p.id_user = u.id_user ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produits.add(new Produit(rs.getInt("id_produit"),
                        rs.getString(2),
                        rs.getString(3),
                        new Categorie(rs.getInt("c.id_categorie"), rs.getString("c.nom_categorie")),
                        rs.getDouble(5),
                        rs.getString(6),
                        new User(rs.getInt("id_user"), rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("role"),
                                rs.getString("nom"),
                                rs.getString("prenom"),
                                rs.getString("adresse"),
                                rs.getString("tel"),
                                rs.getDate("dns")),
                        rs.getDouble(8)
                ));
            }
            // Métier : Tri ascendant des Prix + // Devis transaction
            Produits.stream()
                    .sorted((a, b) -> Double.compare(a.getPrix(), b.getPrix())) // Desc + GroupBY DESC
                    .forEach(x -> System.out.println(" Le prix triée selon l'ordre croissant est : " + x));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // ================  Métier : calcul de promotion avec input de l'administrateur et non automatiquement ============== //
    @Override
    public Double calculerPromotion(jointCategorie jc) {
        if (jc.getPromotionProd() != 0) {
            jc.setPrixProd(jc.getPrixProd() - (jc.getPrixProd() * (jc.getPromotionProd() / 100)));
            System.out.println(" Le prix final aprés promotion est : " + jc.getPrixProd());
        }
        return jc.getPrixProd();
    }

    @Override
    public Produit retrieveProductById(int id) {
        Produit p = new Produit();
        ServiceCategorie sc = new ServiceCategorie();
        String query = "SELECT * FROM `produit` WHERE `id_produit` = " + id + " ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                p.setId_produit(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setReference(rs.getString(3));
                p.setCategorie(sc.retrieveCategorieById(rs.getInt(4)));
                p.setPrix(rs.getInt(5));
                p.setDescription(rs.getString(6));
                p.setUser(new User(rs.getString(7)));
                p.setPrix(rs.getInt(8));
                p.setImage(rs.getString(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Produit> chercherProduitDynamiquement(String s, List<Produit> l) {
        List<Produit> strList = l.stream()
                .map(Produit::concat)
                .filter(pt -> pt.toLowerCase().contains(s.toLowerCase())) //starts with (only searches for a person's id)
                .map(pt -> new Produit(Integer.parseInt(pt.split("-")[0]),
                pt.split("-")[1],
                pt.split("-")[2],
                new Categorie(Integer.parseInt(pt.split("-")[3]), pt.split("-")[4]),
                Double.parseDouble(pt.split("-")[5]),
                pt.split("-")[6],
                new User(pt.split("-")[7]),
                Double.parseDouble(pt.split("-")[8]),
                pt.split("-")[9]
        ))
                //Dans le cas ou je veut limiter le nombre des produits affichés         .limit(5)
                .collect(Collectors.toList());
        return strList;
    }

    @Override
    public void calculatePromotiononAdd() {
        String query = "UPDATE `produit` SET `prix_final`=`prix`-(`prix`*(`promotion`/100))";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public double returnPrixfinal(int id) {
        double prixf = 0;
        ServiceProduit sp = new ServiceProduit();
        String query = "SELECT `prix_final` FROM `produit` WHERE `id_produit` = " + id + " ";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeQuery(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                prixf = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prixf;
    }
}
