/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie.sousFolder;

import boulangerie.DAO.DaoCommander;
import boulangerie.GUI;
import boulangerie.GetSet.Commander;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class FXMLCommandeController extends GUI implements Initializable {
    @FXML
    private AnchorPane principalCommande;
    @FXML private TableView table;
    @FXML private Label numero;
    @FXML private TextField tarif, nomClient;
    @FXML private DatePicker date;
    @FXML private ComboBox<Integer> numeroRepas, numeroResp;
    private DaoCommander dao = new DaoCommander();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // comboBox
        ObservableList rep = this.dao.getNumRepas();
        ObservableList resp = this.dao.getNumResp();
        this.numeroRepas.setItems(rep);
        this.numeroResp.setItems(resp);

        nouvelleCommande();
        refreshTable();
    }   
    @FXML 
    public  void onClickQuit(){
        Stage stage = (Stage)principalCommande.getScene().getWindow();
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
      Stage stage = (Stage)this.principalCommande.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    public void onMouseClickBack() throws IOException{
        this.principalCommande.getChildren().clear();
        Stage stage = (Stage)this.principalCommande.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalCommande.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/FXMLPrincipal.fxml"));
        this.principalCommande.getChildren().add(root);
    }

    private void refreshTable() {
        // recuperer responsables et l'affiche
        
        ObservableList<CommandeData> items = FXCollections.observableArrayList();
        ArrayList<Commander> resp = dao.findAll();
        ListIterator<Commander> li = resp.listIterator();
        while( li.hasNext()) {
            Commander rep = li.next();
            items.add(new CommandeData(
                    rep.getNumCommander(),
                    rep.getTarifCommander(),
                    rep.getDateCommander(),
                    rep.getNomClientCommander(),
                    rep.getNumRepas(),
                    rep.getNumResponsable()
            ));
        }

        String propertyValues[] = {"numero", "nombre", "numRepas", "numResp", "date", "nomCli"};
        super.initTable(this.table, items, propertyValues);
       
    }
    
    @FXML public void enregistrer() {
        int numero = Integer.valueOf( this.numero.getText() );
        String nomCli = this.nomClient.getText();
        String date = this.date.getEditor().getText();
        int numResp = this.numeroResp.getSelectionModel().getSelectedItem();
        int numRepas = this.numeroRepas.getSelectionModel().getSelectedItem();
        int tarif = Integer.valueOf(this.tarif.getText());
        
        // ajout si produit n'existe pas sinon update
        try {
            Commander com = this.dao.find(numero);
            if( com == null) {
                System.out.println("introuvable");
               this.dao.add(numero, tarif, date, nomCli, numRepas, numResp);
            }
            else 
               this.dao.mod(numero, tarif, date, nomCli, numRepas, numResp);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        nouvelleCommande();
        refreshTable();
    }
    
    @FXML public void actualiser() {
        nouvelleCommande();
    }
    @FXML public void modifier() {
         CommandeData cd = (CommandeData) this.table.getSelectionModel().getSelectedItem();
        System.out.println(cd);
        
        int numero = cd.getNumero();
        String nomCli = cd.getNomCli();
        int numRepas = cd.getNumRepas();
        int numResp = cd.getNumResp();
        int tarif = cd.getTarif();
        String date = cd.getDate();
        
        this.numero.setText(String.valueOf(numero));
        this.nomClient.setText(nomCli);
        this.date.getEditor().setText(date);
        this.numeroRepas.getSelectionModel().select(numRepas);
        this.numeroResp.getSelectionModel().select(numResp);
        this.tarif.setText(String.valueOf(tarif));

    }
    @FXML public void supprimer() {
        try {
            CommandeData cd = (CommandeData) this.table.getSelectionModel().getSelectedItem();
            
            int numero = cd.getNumero();
            this.dao.remove(numero);
            
            refreshTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        refreshTable();
    }

    private void nouvelleCommande() {
        int last = dao.getLastNumero();
        this.numero.setText(String.valueOf(last + 1));
        this.date.getEditor().clear();
        this.nomClient.clear();
        this.numeroRepas.getItems().get(0);
        this.numeroResp.getItems().get(0);
        this.tarif.clear();
    }
}
