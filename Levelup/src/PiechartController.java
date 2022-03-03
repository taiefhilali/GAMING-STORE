/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javax.activation.DataSource;
import utils.MaConnexion;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author msi
 */
public class PiechartController implements Initializable {

   
 private Statement st;
    private ResultSet rs;
    private Connection cnx;
    private Stage stage;
    private Scene scene;
    private Parent root;
     ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
    @FXML
    private PieChart Piechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          cnx=MaConnexion.getInstance().getCnx();
        stat();
    }    
   private void stat()
    { 
        
     
      try {
           
          String query ="SELECT COUNT(*),label,resp FROM comment GROUP BY resp" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("label"),rs.getInt("resp")));
            }     
        } catch (Exception ex) {
            //      Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
      
        Piechart.setTitle("**Statistiques most liked comment***");
        Piechart.setLegendSide(Side.LEFT);
        Piechart.setData(data);
    
    }









//Connection c ;
//          data = FXCollections.observableArrayList();
//          try{
//          
//          
//    String SQL = "SELECT COUNT(*),label FROM comment c";
//                   
// 
//            ResultSet rs = cnx.createStatement().executeQuery(SQL);
//            while(rs.next()){
//                //adding data on piechart data
//                data.add(new PieChart.Data(rs.getString(3),rs.getInt(1)));
//            }
//          }catch(Exception e){
//              System.out.println("Error on DB connection");
//              return;
//          }
//    }
//      public void start(Stage stage) throws Exception {
//        //PIE CHART
//        PieChart pieChart = new PieChart();
//       
//        pieChart.getData().addAll(data);
//
//      }}
////           
//     try {
//          
//         String query = "SELECT COUNT(*) ,label,resp FROM comment GROUP BY resp" ;
//      
//            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
//            rs = PreparedStatement.executeQuery();
//           
//                   
//           while (rs.next()){               
//             data.add(new PieChart.Data(rs.getString("label"),rs.getInt("COUNT(*)")));
//           }     
//       } catch (Exception ex) {
//        }
//     
   
      // Piechart.setData(data);

    @FXML
    private void statret(ActionEvent event) {
        
        
         try {
            root = FXMLLoader.load(getClass().getResource("Show_CommentFXML.fxml"));
            stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
   
 }
    

    
    