/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import interfaces.ICommande;
import interfaces.ICommande_elem;
import interfaces.IPanier_elem;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import models.Commande;
import models.Commande_elem;
import models.PanProd;
import models.Panier_elem;
import models.ProduitPanier;
import services.ServiceCommande;
import services.ServiceCommande_elem;
import services.ServicePanier_elem;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichagePanierController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
   static List<Commande_elem> p3 = new ArrayList<Commande_elem>();
      ICommande sp1 = new ServiceCommande();
      FX_LoginController fx = new FX_LoginController();
    //AcceuilController ac = new AcceuilController();
    IPanier_elem sp = new ServicePanier_elem();
    static Commande cmd = new Commande();
    static List<Panier_elem> Li = new ArrayList<>();
    static List<Integer> Li1 = new ArrayList<>();
     ICommande_elem sp2 = new ServiceCommande_elem();
     
     
    private PanProd produit;
    @FXML
    private Button Retour;
    @FXML
    private Button validerC;
    @FXML
    private Label sommeProduits;
    @FXML
    private Label Livraison;
    @FXML
    private Label TotalP;
    @FXML
    private Button calcule;
    @FXML
    private ImageView logoImage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    int i =0;
        List<ProduitPanier> p5 = new ArrayList<ProduitPanier>();
      private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          //Animation
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(logoImage);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
        
        
        int column = 0 ;
        int row = 1;
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
         List<Commande_elem> p3 = new ArrayList<Commande_elem>();
        p1 = sp.afficherPanier(fx.p.getId_panier());
        try {
        for(Panier_elem p2 : p1){
           PanProd p = new PanProd(p2.getId_elem(),p2.getProduit().getId_produit(),p2.getProduit().getNom(),p2.getProduit().getPrix(),p2.getProduit().getImage(),p2.getQuantite());
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("PanierItem.fxml"));
                AnchorPane anchorpane = fxmlloader.load();
                PanierItemController panieritem = fxmlloader.getController();
                    panieritem.setData(p);
                    p5.add(panieritem.getData());
                
                if(column == 2){
                    column = 0;
                    row++;
                }
                
                grid.add(anchorpane, column++, row);
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorpane, new Insets(10));
                
        }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       
        
    }    

 
    
    @FXML
    private void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void valider(ActionEvent event) throws IOException, Exception {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation !!!");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de Valider Votre Commande ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        
         if (action.get() == ButtonType.OK){
         if(p5.size() != 0){
             Double Total = 0.0;
                Double Liv = 0.0;
                for(ProduitPanier p2 : p5){
                     Li1.add(p2.getQuantite());
                    Total += p2.getPrix() * p2.getQuantite();
                }
                if(Total<200 && p5.size() != 0){
                 Liv =10.0; 
                 }
                Double Tot = Total + Liv;
                LocalDate d = LocalDate.now();
                Date d1 = Date.valueOf(d);
                cmd.setDate_commande(d1);
                cmd.setPrix_livraison(Liv);
                cmd.setPrix_produits(Total);
                cmd.setPrix_total(Tot);
                cmd.setClient(fx.p.getClient());
                this.affectation();
                sp1.ajouterCommande(cmd);
                 this.AjouterCommande();
                this.PDFCreation();
                 this.javaMail(utils.Session.getEmail());
                 showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "Commande Ajouter !!!", "Facture Envoyer avec Succés : Consulter votre Compte E-mail !!!");
         
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         }
         else{
             showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                           "Alert!", "Impossible de passer une commande : Panier est Vide");
         }
         }
         else {
             alert1.close();
           }
    }

     public void AjouterCommande() throws IOException{
        List<Panier_elem> l = new ArrayList<>();
        l = Li;
        LocalDate d = LocalDate.now();
        Date d1 = Date.valueOf(d);
        Commande c = new Commande();
        c = sp1.GetCommande(fx.p.getClient().getId(),d1);
        int i =0;
        while(i < Li.size()){
            Commande_elem e = new Commande_elem(Li.get(i).getProduit(),c,(int) Li1.get(i));
            p3.add(e);
            sp2.ajouterElementCommande(e);
            i++;
        }
       for(Panier_elem e : l){
           sp.supprimerElementPanier(e);
       }
   
    }
    
    public void affectation(){
        List<Panier_elem> p1 = new ArrayList<Panier_elem>();
        p1 = sp.afficherPanier(1);
        Li = p1;
    }
    
    @FXML
    private void calculer(ActionEvent event) throws IOException {
                
        Double Total = 0.0;
                Double Liv = 0.0;
                for(ProduitPanier p2 : p5){
                    Total += p2.getPrix() * p2.getQuantite();
                }
                if(Total<200 && p5.size() != 0){
                 Liv =10.0; 
                 }
                Double Tot = Total + Liv;
                sommeProduits.setText(Total.toString()+"  DT");
                 Livraison.setText(Liv.toString()+"  DT");
                 TotalP.setText(Tot.toString()+"  DT");
                 
    }
    
    //Mailing Function
        public void javaMail(String recepteur) throws Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
          properties.put("mail.smtp.host", "smtp.gmail.com");
           properties.put("mail.smtp.port", "587");
           
           String email ="hazembayoudh886@gmail.com";
           String password ="hazem+19";
           
           Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password); 
            }
           });
           
           Message message = prepareMessage(session,email,recepteur);
           Transport.send(message);
           System.out.println("Message sent successfully !!!!!");
    }
    
    //E-Mail Message Content
          private static Message prepareMessage(Session session, String email, String recepteur){
         try {
             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(email));
             message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepteur));
             message.setSubject("My first Email from java application");
             message.setText("");
             
             Multipart emailcontent = new MimeMultipart();
             MimeBodyPart textBodyPart = new MimeBodyPart();
             textBodyPart.setText("Votre Facture  :");
             
             MimeBodyPart PDFBodyPart = new MimeBodyPart();
             PDFBodyPart.attachFile("C:\\Users\\beldi\\Desktop\\Levelup\\FACTURE.pdf");
             
             emailcontent.addBodyPart(textBodyPart);
             emailcontent.addBodyPart(PDFBodyPart);
             
             message.setContent(emailcontent);
             
             return message;
         } catch (Exception ex) {
             Logger.getLogger(AffichagePanierController.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
           }
          
          
          
          //PDF Creation
          public void PDFCreation()throws IOException, DocumentException{
                Document doc = new Document();
                PdfWriter.getInstance(doc,new FileOutputStream("FACTURE.pdf"));
                doc.setPageSize(PageSize.A4);
                
                Font f=new Font(Font.FontFamily.TIMES_ROMAN,22.0f,Font.BOLD,BaseColor.RED);
                Font f1=new Font(Font.FontFamily.TIMES_ROMAN,15.0f,Font.NORMAL,BaseColor.BLACK);
                Paragraph p7 = new Paragraph("\n");
   
                Paragraph p1 = new Paragraph ("1) Information du Client    :",f);
                Paragraph p10 = new Paragraph("2) Information du Commande  :",f);
                Paragraph p = new Paragraph ("Nom Client                  :      "+cmd.getClient().getNom(),f1);
                Paragraph p9 = new Paragraph("Prenom du Client            :      "+cmd.getClient().getPrenom(),f1);
                Paragraph p2 = new Paragraph("Date_Commande               :      "+cmd.getDate_commande(),f1);
                Paragraph p3 = new Paragraph("Prix Livraison              :      "+cmd.getPrix_livraison()+"    DT",f1);
                Paragraph p4 = new Paragraph("Total des produits          :      "+cmd.getPrix_produits()+"    DT",f1);
                Paragraph p5 = new Paragraph("Montant Total a payer       :      "+cmd.getPrix_total()+"    DT",f1);
                 
                
                doc.open();
                Image image = Image.getInstance("C:\\Users\\beldi\\Desktop\\Levelup\\src\\images\\Entete.png");
                image.scaleAbsoluteWidth(600);
                image.scaleAbsoluteHeight(92);
                
                 image.setAlignment(image.ALIGN_CENTER);
                 doc.add(image);
                 doc.add(new Paragraph(" "));
                 doc.add(p1);
                 doc.add(new Paragraph(" "));
                 doc.add(p7);
                 doc.add(p);
                 doc.add(p9);
                 doc.add(p2);
                 doc.add(p3);
                 doc.add(p4);
                 doc.add(p5);
                 doc.add(new Paragraph(" "));
                 doc.add(p7);
                 doc.add(p10);
                 doc.add(new Paragraph(" "));
                 doc.add(p7);
                 PdfPTable table = new PdfPTable(3);
                 table.setWidthPercentage(100);
                 PdfPCell cell;
                 
                 cell = new PdfPCell(new Phrase("Nom Produit",FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.GRAY);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase("Prix",FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.GRAY);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase("Quantité",FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.GRAY);
                 table.addCell(cell);
                 
                 for(Commande_elem s: this.p3){
                     cell = new PdfPCell(new Phrase(s.getProduit().getNom(),FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.WHITE);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(String.valueOf(s.getProduit().getPrix()),FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.WHITE);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(String.valueOf(s.getQuantite()),FontFactory.getFont("Comic Sans MS", 12)));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setBackgroundColor(BaseColor.WHITE);
                 table.addCell(cell);
                 }
                 doc.add(table);
                
                doc.close();   
          }
}
