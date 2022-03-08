/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import interfaces.Icategorie;
import interfaces.Iproduit;
import interfaces.Iuser;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Desktop;
import static java.awt.SystemColor.text;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Categorie;
import models.Produit;
import models.User;
import services.ServiceCategorie;
import services.ServiceProduit;
import services.ServiceUser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.text.View;
import org.apache.poi.ss.usermodel.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.layout.AnchorPane;
import static javax.swing.Spring.height;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import static java.lang.Thread.sleep;
import javafx.application.HostServices;
import javax.swing.text.Document;
import models.jointCategorie;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * FXML Controller class
 *
 * @author Iskander
 */
public class AddProductInterfaceController implements Initializable {

// Déclaration des variables utilisées
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final ObservableList<Produit> obsProduit = FXCollections.observableArrayList();
    Iproduit sp = new ServiceProduit();
    Iuser su = new ServiceUser();
    List<Produit> listProd = sp.afficherProduit();

    // Fichier de l'image
    File selectedFile;
    private String path;
    QRCodeWriter qrCodeWriter;
    // Déclaration du List de produit

    @FXML
    private TextField nomProduitTF;
    @FXML
    private TextField referenceTF;
    @FXML
    private TextField prixTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField promotionTF;
    @FXML
    private TableColumn<jointCategorie, String> colNomProd;
    @FXML
    private TableColumn<jointCategorie, String> colReference;
    @FXML
    private TableColumn<jointCategorie, String> colCategorie;
    @FXML
    private TableColumn<jointCategorie, Double> colPrix;
    @FXML
    private TableColumn<jointCategorie, String> colDescription;
    @FXML
    private TableColumn<jointCategorie, String> colFournisseur;
    @FXML
    private TableColumn<jointCategorie, Double> colPromotion;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSuppression;
    @FXML
    private Button btnModif;
    @FXML
    private Button btn_retour;
    @FXML
    private TableView<jointCategorie> productsTV;
    @FXML
    private ComboBox comboBoxCat;
    @FXML
    private ComboBox comboFourn;
    @FXML
    private TableColumn<jointCategorie, Integer> colIdProd;
    @FXML
    private TextField ChercheTF;

    // Le bouton de calcul de promotion metier
    @FXML
    private TableColumn<jointCategorie, jointCategorie> col_Action = new TableColumn<>("Calculer Promotion");
    @FXML
    private TextField id_Produit;
    @FXML
    private Button image;
    @FXML
    private ImageView screenshotView;
    @FXML
    private Button btnqr;
    @FXML
    private Button btnexport;
    @FXML
    private AnchorPane anchorFormulaire;
    @FXML
    private Button dbButton;

    /**
     * Initializes the controller class.
     */
    // Création d'une alerte lors du crud
    private boolean showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        // Désactivation des boutons
////        btnModif.setDisable(true);
//        btnSuppression.setDisable(true);
////        btnqr.setDisable(true);
//      anchorFormulaire.setOpacity(0);
        List<Produit> prodDataBase = sp.afficherProduit();
        Produit p2 = new Produit();

        // Méthode de séléction d'un élement du TV aprés clique sur le curseur
        productsTV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (productsTV.getSelectionModel().getSelectedItem() != null) {
                    jointCategorie selectedProduit = productsTV.getSelectionModel().getSelectedItem();
//                    System.out.println(selectedProduit);
                    id_Produit.setText(String.valueOf(selectedProduit.getIdProd()));
                    nomProduitTF.setText(selectedProduit.getNomProd());
                    referenceTF.setText(selectedProduit.getRefProd());
                    comboBoxCat.setValue(selectedProduit.getNomCat());
                    String p = String.valueOf(selectedProduit.getPrixProd());
                    prixTF.setText(p);
                    descriptionTF.setText(selectedProduit.getDescriptionProd());
                    comboFourn.setValue(selectedProduit.getEmailUser());

//                    image.setselectedProduit(selectedProduit.getImage()));
//                    int index = selectedProduit.getUser().toString().indexOf("email");
//                    int indexlast = selectedProduit.getUser().toString().indexOf(", password");
                    String a = String.valueOf(selectedProduit.getPromotionProd());
                    promotionTF.setText(a);

//                    selectedProduit.setImage(selectedProduit.getImage());
//                    (image.getContentDisplay(selectedProduit.getImage());
                } else {
                    productsTV.getSelectionModel().clearSelection();

                }
            }
        }
        );

        // Affichage du combo box 1 : Catégories combo box
        Icategorie sc = new ServiceCategorie();
        List<Categorie> Categories = sc.afficherCategorie();
        Categories.forEach(
                (c) -> {
                    comboBoxCat.getItems().add(c.getNom_categorie());
                }
        );
        // Affichage du combo box 2 : ID + Email fournisseur Combo box
        Iuser su = new ServiceUser();
        List<User> listUser = su.afficherPersonnes();
        listUser.forEach(
                (u) -> {
                    comboFourn.getItems().add(u.getEmail());
                }
        );
        afficherProds("");
    }
    // Fonction Affichage

    public void afficherProds(String input) {

//        listProd = sp.chercherProduitDynamiquement(input, sp.afficherProduit());
        Icategorie c = new ServiceCategorie();

// Affichge des attributs produits
        colNomProd.setCellValueFactory(
                new PropertyValueFactory<>("nomProd"));
        colReference.setCellValueFactory(
                new PropertyValueFactory<>("RefProd"));
        colCategorie.setCellValueFactory(
                new PropertyValueFactory<>("NomCat"));
        colPrix.setCellValueFactory(
                new PropertyValueFactory<>("PrixProd"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("descriptionProd"));
        colFournisseur.setCellValueFactory(
                new PropertyValueFactory<>("emailUser"));
        colPromotion.setCellValueFactory(
                new PropertyValueFactory<>("PromotionProd"));
        colIdProd.setCellValueFactory(
                new PropertyValueFactory<>("idProd"));
        listProd.forEach(
                (p) -> {
                    productsTV.getItems().addAll(
                            new jointCategorie(p.getNom(),
                                    p.getReference(), p.getCategorie().getNom_categorie(), p.getPrix(),
                                    p.getDescription(), p.getUser().getEmail(), p.getPromotion(), p.getId_produit())
                    );

                    col_Action.setCellValueFactory(
                            param -> new ReadOnlyObjectWrapper<>(param.getValue())
                    );
                    col_Action.setCellFactory(param -> new TableCell<jointCategorie, jointCategorie>() {
                private final Button calculProm = new Button("Calculer");
//                calculProm.setPrefWidth(97);

                @Override
                protected void updateItem(jointCategorie jc, boolean empty) {
                    super.updateItem(jc, empty);
                    if (jc == null) {
                        setGraphic(null);
                        return;
                    }
                    calculProm.setPrefSize(97, 1);
                    calculProm.setStyle("-fx-background-color: #FFD700;"); // Couleur dorée du nv bouton
                    setGraphic(new HBox(calculProm));
                    calculProm.setOnAction(
                            event -> {

                                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                                        "Calcul de la Promotion ", " Le prix final aprés le calcul de la promotion est : " + (sp.calculerPromotion(p))
                                );
                            }
                    );
                }
            });
                }
        );

    }

    @FXML
    private void ajouterProduitAction(ActionEvent event) throws IOException {
        Iproduit sp = new ServiceProduit();
        Iuser su = new ServiceUser();
        Icategorie sc = new ServiceCategorie();

        // Affichage d'une alerte de confirmation
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation d'ajout");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de vouloir ajouter ce produit ? ");
        Optional<ButtonType> action = alert1.showAndWait();

        if (action.get() == ButtonType.OK) {
            // Controle de saisie
            if ((nomProduitTF.getText().isEmpty())
                    || (comboBoxCat.getValue() == null)
                    || (referenceTF.getText().isEmpty())
                    || (prixTF.getText().isEmpty())
                    || (descriptionTF.getText().isEmpty())
                    || (comboFourn.getValue() == null)
                    || (promotionTF.getText().isEmpty())
                    || (selectedFile == null)) { // ajout des produits
                System.out.println("valeur null");
                showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                        " Erreur d'ajout du produit ! ", " Erreur d'ajout! \n Veuillez remplir touts les champs! ");
            } else {
                sp.ajouterProduit(new Produit(nomProduitTF.getText(),
                        referenceTF.getText(),
                        sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                        Double.parseDouble(prixTF.getText()),
                        descriptionTF.getText(),
                        su.getByEmail((String) comboFourn.getValue()),
                        Double.parseDouble(promotionTF.getText()),
                        selectedFile.getAbsolutePath().replace("\\", "/")));
                sp.calculatePromotiononAdd();
// this.image.getText();

                // ajout<<
                root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés d'ajout ! ", " Ajout du produit établie avec succés! ");
            }

        } else {
            alert1.close();
        }
    }

// CRUD SUPPRESSION PRODUIT
    @FXML

    private void supprimerProduitAction(ActionEvent event) throws IOException {
        Icategorie sc = new ServiceCategorie();
        // Alerte de confirmation du suppression
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation d'ajout");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de vouloir supprimer ce produit ? ");
        Optional<ButtonType> action = alert1.showAndWait();

        if (action.get() == ButtonType.OK) {
            sp.supprimerProduit(new Produit(Integer.parseInt(id_Produit.getText()), nomProduitTF.getText(),
                    referenceTF.getText(),
                    sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                    Double.parseDouble(prixTF.getText()),
                    descriptionTF.getText(),
                    su.getByEmail((String) comboFourn.getValue()),
                    Double.parseDouble(promotionTF.getText()))
            );
            root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                    " Succés de suppression ! ", " Suppression du produit établie avec succés! ");
//        } else {
//            alert1.close();
//        }
        }
    }

// CRUD MODIFICATION 
    @FXML
    private void modifierProduitAction(ActionEvent event) throws IOException {
        Iuser su = new ServiceUser();
        Iproduit sp = new ServiceProduit();
        Icategorie sc = new ServiceCategorie();

        // Alerte de confirmation de modification
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation d'ajout");
        alert1.setHeaderText(null);
        alert1.setContentText(" Etes-vous sure de vouloir modifier ce produit ? ");
        Optional<ButtonType> action = alert1.showAndWait();

        if (action.get() == ButtonType.OK) {
            if ((nomProduitTF.getText().isEmpty())
                    || (comboBoxCat.getValue() == null)
                    || (referenceTF.getText().isEmpty())
                    || (prixTF.getText().isEmpty())
                    || (descriptionTF.getText().isEmpty())
                    || (comboFourn.getValue() == null)
                    || (promotionTF.getText().isEmpty())) { // ajout des produits
                System.out.println("valeur null");
                showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                        " Erreur de modification ! ", " Erreur de modification! \n Veuillez remplir touts les champs! ");
            } else {
                sp.modifierProduit(new Produit(Integer.parseInt(id_Produit.getText()), nomProduitTF.getText(),
                        referenceTF.getText(),
                        sc.retrieveCategorieByNom((String) comboBoxCat.getValue()),
                        Double.parseDouble(prixTF.getText()),
                        descriptionTF.getText(),
                        su.getByEmail((String) comboFourn.getValue()),
                        Double.parseDouble(promotionTF.getText())));

                sp.calculatePromotiononAdd();
                root = FXMLLoader.load(getClass().getResource("./AddProductInterface.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                        " Succés! ", " Modification du produit établie avec succés! ");
            }
        } else {
        }
    }

// Insertion d'une Image
    @FXML
    private void image(ActionEvent event) throws MalformedURLException {

        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez choisir l'image du produit en question ");
        String userDirectoryString = System.getProperty("user.home") + "/Desktop";
        File userDirectory = new File(userDirectoryString);
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            path = selectedFile.getName();
            // Affichage dans une liste view invisible
            screenshotView.setImage(new Image(selectedFile.toURI().toURL().toString()));
            screenshotView.setFitHeight(150);
            screenshotView.setFitWidth(250);
            image.setText(path);
            System.out.println(selectedFile.getAbsolutePath());

//            String req = "INSERT into produit SET image= '" + selectedFile.getAbsolutePath().replace("/", "/")+"' WHERE id_produit = " + p.getId_produit() + " ";
//
//                Statement st = cnx.createStatement();
//                st.executeUpdate(req);
        }
    }

    // Fonction de recherche assosié au bouton de recher rechercheTF
    @FXML
    private void chercherProduits(KeyEvent event) {

        listProd = sp.chercherProduitDynamiquement(ChercheTF.getText(), sp.afficherProduit());
        productsTV.getItems().clear();
        afficherProds(ChercheTF.getText());

    }

    @FXML
    private void clearonWhiteSpace(MouseEvent event) {

        id_Produit.setText("");
        nomProduitTF.setText("");
        referenceTF.setText("");
        comboBoxCat.setValue("");
        prixTF.setText("");
        descriptionTF.setText("");
        comboFourn.setValue("");
        promotionTF.setText("");

    }

    //QR Code Generation API   
    @FXML
    private void generateQRCode(MouseEvent event) throws WriterException, IOException, InterruptedException {

        Icategorie sc = new ServiceCategorie();
        String qrCodeText = "-Le nom du produit est : "
                + nomProduitTF.getText()
                + ".\n============="
                + ". \n-Sa reference est : "
                + referenceTF.getText()
                + ".\n============="
                + ". \n-Son prix est : "
                + Double.parseDouble(promotionTF.getText())
                + ".\n============="
                + ".\n-Sa description est : " + descriptionTF.getText();

        // Vérification de l'emplacement de l'image
        String filePath = "D:/PiPictures/ProduitQR.png";
        // "C:/Users/zaba2/Desktop/Functional APIs Lundi/Levelup/ProduitQR.png";
        // Change path not to ecrase old file
        int size = 500;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation d'ajout");
        alert1.setHeaderText(null);
        alert1.setContentText(" Créer le code QR ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {

            if (showAlert(Alert.AlertType.INFORMATION, ((Node) event.getSource()).getScene().getWindow(),
                    " Succés de création ! ", " Génération avec succés du code QR du produits ! " + "\n " + nomProduitTF.getText())) {
                File f1 = new File("D:/PiPictures/ProduitQR.png");
                Desktop d = Desktop.getDesktop();
                d.open(f1);
//                root = FXMLLoader.load(getClass().getResource("./InterfaceCodeQR.fxml"));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);

//            Thread.sleep(2);
//C:\Users\zaba2\Desktop\Functional APIs Lundi\Levelup
                System.out.println(" Création du code QR avec succés ");
                createQRImage(qrFile, qrCodeText, size, fileType);
            }

        }
    }

    // Execution du méthode du codage
    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 500, 500, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);

    }

    // Fonction du bouton Export as Barcode PDF
    // Code a bar
    @FXML
    private void exportAsBarcode(ActionEvent event) throws IOException {

        // alerte de confirmation
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation de création");
        alert1.setHeaderText(null);
        alert1.setContentText(" Créer le code à bar ? ");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {

//            root = FXMLLoader.load(getClass().getResource("./InterfaceBarcode.fxml"));
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
            String barCodePath = "D:/PiPictures/";
            //careful badalt el \\ b / f all file:  careful f getAbsoluePath might have been changed
            String fileName = nomProduitTF.getText() + " Barcode";
            Code39Bean bean39 = new Code39Bean();
            final int dpi = 180;
            //Configure the barcode generator
            bean39.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
            bean39.doQuietZone(false);
            //Open output file
            File outputFile = new File(barCodePath + fileName + ".JPG");
            FileOutputStream out = new FileOutputStream(outputFile);
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //Generate the barcode
            bean39.generateBarcode(canvas, nomProduitTF.getText());
            File f = new File("D:/PiPictures/" + nomProduitTF.getText() + " Barcode.JPG");
            //Signal end of generation
            canvas.finish();
            System.out.println(" Création avec succés du Code à Bar ");
            // Ouverture
            Desktop d = Desktop.getDesktop();
            d.open(f);

        }
    }

    @FXML
    private void switchTo(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("./SelectionInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // fonction génératrice de pdf
//    public void PDFCreation() throws IOException, DocumentException {
//        Document doc = new Document();
//        PdfWriter.getInstance(doc, new FileOutputStream("CodesProduits.pdf"));
//        doc.setPageSize(PageSize.A4);
//    }
    // Bouton permettant d'exporter sous forme de fichier excel / 
    @FXML
    private void visitDB(MouseEvent event) throws IOException {
//        Writer writer = null;
//        File file = new File("D:\\PiPictures\\Produit.csv.");
//        writer = new BufferedWriter(new FileWriter(file));
//        for (Produit p : listProd) {
//
//            String text = p.getNom() + "," + p.getPrix() + "," + p.getCategorie().getNom_categorie() + ","
//                    + p.getPrix() + "," + p.getPromotion()
//                    + "," + p.getImage() + "," + p.getUser().getEmail() + "\n";
//            file.setReadable(true);
//            file.setWritable(true);
//            writer.write(text);
//            System.out.println("successfuly generated excel file");
//            writer.flush();
//
//        }
//    }

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Details Produit");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nom Produit");
        header.createCell(1).setCellValue(" Référence ");
        header.createCell(2).setCellValue("Prix");
        header.createCell(3).setCellValue(" Email Fournisseur ");
        header.createCell(4).setCellValue("Promotion");

        int index = 1;
        listProd.forEach(
                (p) -> {
                    
                    
                }
                    );
                }
    }
