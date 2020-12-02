/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie.sousFolder;

import boulangerie.DAO.DaoResponsables;
import boulangerie.GUI;
import boulangerie.GetSet.Repas;
import boulangerie.GetSet.Responsables;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLResponsablesController extends GUI implements Initializable {
    
    @FXML
    private AnchorPane principalResponsables;
    
    @FXML private TableView table;
    @FXML private Label numero;
    @FXML private TextField nom, pseudo, pass, contact, droit;
    
    private DaoResponsables dao = new DaoResponsables();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int last = this.dao.getLastNumero();
        this.numero.setText(String.valueOf(last + 1));
        System.out.println(last);
        
        initializeTable();
    } 
    
    @FXML 
    public  void onClickQuit(){
        Stage stage = (Stage)principalResponsables.getScene().getWindow();
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
      Stage stage = (Stage)this.principalResponsables.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    public void onMouseClickBack() throws IOException{
        this.principalResponsables.getChildren().clear();
        Stage stage = (Stage)this.principalResponsables.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalResponsables.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/FXMLPrincipal.fxml"));
        this.principalResponsables.getChildren().add(root);
    }

    private void initializeTable() {
        // recuperer responsables et l'affiche
        try {
            ObservableList<ResponsableData> items = FXCollections.observableArrayList();
            ArrayList<Responsables> resp = dao.findAll();
            ListIterator<Responsables> li = resp.listIterator();
            while( li.hasNext()) {
                Responsables rep = li.next();
                items.add(new ResponsableData(
                        rep.getNumResponsable(),
                        rep.getNomResponsable(),
                        rep.getPseudoResponsable(),
                        rep.getPasswordResponsable(),
                        rep.getTelResponsable(),
                        rep.getDroitResponsable()
                ));
            }
            
            String propertyValues[] = {"numero", "nom", "pseudo", "pass", "contact", "droit"};
            super.initTable(this.table, items, propertyValues);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML public void supprimer() {
        try {
            ResponsableData rd = (ResponsableData) this.table.getSelectionModel().getSelectedItem();
            
            int numero = rd.getNumero();
            this.dao.remove(numero);
            
            initializeTable();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLResponsablesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLResponsablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void modifier() {
        ResponsableData rd = (ResponsableData) this.table.getSelectionModel().getSelectedItem();
        System.out.println(rd);
        
        int numero = rd.getNumero();
        String nom = rd.getNom();
        String contact = rd.getContact();
        String droit = rd.getDroit();
        String pass = rd.getPass();
        String pseudo = rd.getPseudo();

        this.numero.setText(String.valueOf(numero));
        this.nom.setText(nom);
        this.contact.setText(contact);
        this.pass.setText(pass);
        this.pseudo.setText(pseudo);
        this.droit.setText(droit);
    }
    
    @FXML public void nouveau() {
        nouveauResponsable();
    }
    
    @FXML public void enregistrer() {
        int numero = Integer.valueOf( this.numero.getText() );
        String nom = this.nom.getText();
        String pass = this.pass.getText();
        String pseudo = this.pseudo.getText();
        String contact = this.contact.getText();
        String droit = this.droit.getText();
        
        // ajout si produit n'existe pas sinon update
        Responsables rep;
        try {
            rep = this.dao.find(numero);
            if( rep == null)
               this.dao.add(numero, nom, pseudo, pass, contact, droit);
            else 
               this.dao.mod(numero, nom, pseudo, pass, contact, droit);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        // refresh
        nouveauResponsable();
        initializeTable();
    }
     
    private void nouveauResponsable() {
        int last = this.dao.getLastNumero();
        this.numero.setText(String.valueOf(last + 1));
        this.nom.clear();
        this.pass.clear();
        this.pseudo.clear();
        this.contact.clear();
        this.droit.clear();
    }
    
}
