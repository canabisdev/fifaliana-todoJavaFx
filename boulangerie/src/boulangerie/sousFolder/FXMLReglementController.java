/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie.sousFolder;

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
public class FXMLReglementController implements Initializable {

    @FXML
    private AnchorPane principalReglement;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML 
    public  void onClickQuit(){
        Stage stage = (Stage)principalReglement.getScene().getWindow();
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
      Stage stage = (Stage)this.principalReglement.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    public void onMouseClickBack() throws IOException{
        this.principalReglement.getChildren().clear();
        Stage stage = (Stage)this.principalReglement.getScene().getWindow();
        stage.setResizable(false);
        Scene scene = this.principalReglement.getScene();
        scene.getStylesheets().clear();
        scene.setFill(Color.TRANSPARENT);
        Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/FXMLPrincipal.fxml"));
        this.principalReglement.getChildren().add(root);
    }
    
}
