/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie.sousFolder;

import boulangerie.DAO.DaoRepas;
import boulangerie.GetSet.Repas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import boulangerie.GUI;
import java.util.ListIterator;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;


/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLRepasController extends GUI implements Initializable  {
    @FXML
    private AnchorPane principalRepas;
    @FXML private Label labNumero;
    @FXML private TextField nom, prix, quantite;
    @FXML private TableView table;
    
    private DaoRepas dao = new DaoRepas();
    
    public FXMLRepasController() {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // numero
        int lastNum = dao.getLastNumero();
        this.labNumero.setText(String.valueOf(lastNum + 1));
        
        // table
        initializeTable();
    }
    @FXML 
    public  void onClickQuit(){
        Stage stage = (Stage)principalRepas.getScene().getWindow();
        stage.close();
    } 
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public void OnMousePressed(MouseEvent event)
    {
      this.xOffset = event.getSceneX();
      this.yOffset = event.getSceneY();
    }

    public void OnMouseDragged(MouseEvent event)
    {
      Stage stage = (Stage)this.principalRepas.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    public void onMouseClickBack() throws IOException{
        this.principalRepas.getChildren().clear();
        Stage stage = (Stage)this.principalRepas.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalRepas.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/FXMLPrincipal.fxml"));
        this.principalRepas.getChildren().add(root);
    }
    
    @FXML public void enregistrerProduit() throws SQLException, ClassNotFoundException {
        int numeroRepas = Integer.valueOf( this.labNumero.getText() );
        String nomRepas = this.nom.getText();
        int prix = Integer.valueOf( this.prix.getText() );
        int quantite = Integer.valueOf( this.quantite.getText() );

        // ajout si produit n'existe pas sinon update
        Repas rep = this.dao.find(numeroRepas);
        if( rep == null)
          this.dao.add(numeroRepas, nomRepas, prix, quantite);
        else 
          this.dao.mod(numeroRepas, nomRepas, prix, quantite);
        
        // refresh
        nouveauProduit();
        initializeTable();
    }
    
    @FXML public void nouveau() {
        // nouveau produit
        
        nouveauProduit();
    }
    
    private void nouveauProduit() {
        int last = this.dao.getLastNumero();
        this.labNumero.setText(String.valueOf(last + 1));
        this.nom.clear();
        this.prix.clear();
        this.quantite.clear();
    }
    
    @FXML public void modifierProduit() {
        RepasData rd = (RepasData) this.table.getSelectionModel().getSelectedItem();
        
        int numero = rd.getNumero();
        String nom = rd.getNom();
        int prix = rd.getPrix();
        int quantite = rd.getQuantite();

        this.labNumero.setText(String.valueOf(numero));
        this.nom.setText(nom);
        this.prix.setText(String.valueOf(prix));
        this.quantite.setText(String.valueOf(quantite));
        
    }
    
    @FXML public void supprimerProduit() {
        RepasData rd = (RepasData) this.table.getSelectionModel().getSelectedItem();
        
        int numero = rd.getNumero();
        this.dao.remove(numero);

        initializeTable();
        
    }

    private void initializeTable() {
        // recuperer repas et l'affiche
        try {
            ObservableList<RepasData> items = FXCollections.observableArrayList();
            ArrayList<Repas> repas = dao.findAll();
            ListIterator<Repas> li = repas.listIterator();
            while( li.hasNext()) {
                Repas rep = li.next();
                items.add(new RepasData(
                  rep.getNumRepas(),
                  rep.getPrixRepas(),
                  rep.getQtRepas(), rep.getNomRepas())
                );
                }
            
            String propertyValues[] = {"numero", "nom", "prix", "quantite"};
            super.initTable(this.table, items, propertyValues);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}
