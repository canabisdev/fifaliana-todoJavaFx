/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author blackran
 */
public class FXMLPrincipalController implements Initializable {
    
    @FXML private AnchorPane principalDesktop;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
      Stage stage = (Stage)this.principalDesktop.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    @FXML 
    public  void onClickQuit(){
        Stage stage = (Stage)principalDesktop.getScene().getWindow();
        stage.close();
    } 
    @FXML
    public void onClickResponsable() throws IOException{
        this.principalDesktop.getChildren().clear();
        Stage stage = (Stage)this.principalDesktop.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalDesktop.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/sousFolder/FXMLResponsables.fxml"));
        this.principalDesktop.getChildren().add(root);
    }
    @FXML
    public void onClickRepas() throws IOException{
        this.principalDesktop.getChildren().clear();
        Stage stage = (Stage)this.principalDesktop.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalDesktop.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/sousFolder/FXMLRepas.fxml"));
        this.principalDesktop.getChildren().add(root);
    }
    @FXML
    public void onClickCommande() throws IOException{
        this.principalDesktop.getChildren().clear();
        Stage stage = (Stage)this.principalDesktop.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalDesktop.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/sousFolder/FXMLCommande.fxml"));
        this.principalDesktop.getChildren().add(root);
    }
    @FXML
    public void onClickReglements() throws IOException{
        this.principalDesktop.getChildren().clear();
        Stage stage = (Stage)this.principalDesktop.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalDesktop.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/sousFolder/FXMLReglement.fxml"));
        this.principalDesktop.getChildren().add(root);
    }
}
