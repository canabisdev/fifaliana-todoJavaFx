/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulangerie;

import boulangerie.DAO.DaoResponsables;
import boulangerie.GetSet.Responsables;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author blackran
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnClose;
    
    @FXML
    private StackPane principalLogin;
    
    @FXML
    private TextField PseudoLogin;
    
    @FXML
    private PasswordField PasswordLogin;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML public  void onClickQuit(){
        Stage stage = (Stage)btnClose.getScene().getWindow();
        stage.close();
    }
    public void log() throws IOException, SQLException, ClassNotFoundException{
      DaoResponsables resp = new DaoResponsables();
      ArrayList<Responsables> respArr = resp.searchAll(this.PseudoLogin.getText());
      if (!respArr.isEmpty())
      {
        this.PasswordLogin.setStyle("");
        for (int i = 0; i < respArr.size(); i++) {
          boolean okPseudo = (((Responsables)respArr.get(i)).getPseudoResponsable().equals(this.PseudoLogin.getText()));
          boolean okPass = (((Responsables)respArr.get(i)).getPasswordResponsable().equals(this.PasswordLogin.getText()));
          if ( okPseudo && okPass)
          {
            this.PasswordLogin.setStyle("");
            this.principalLogin.getChildren().clear();
            Stage stage = (Stage)this.principalLogin.getScene().getWindow();
            stage.setResizable(false);
            Scene scene = this.principalLogin.getScene();
            scene.getStylesheets().clear();
            scene.setFill(Color.TRANSPARENT);
            Parent root = FXMLLoader.load(getClass().getResource("/boulangerie/FXMLPrincipal.fxml"));
            this.principalLogin.getChildren().add(root);
            stage.setWidth(1000);
            stage.setHeight(700);
            
            javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
          }
        }
      } else {
          
      }
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
      Stage stage = (Stage)this.principalLogin.getScene().getWindow();
      stage.setX(event.getScreenX() - this.xOffset);
      stage.setY(event.getScreenY() - this.yOffset);
    }
    @FXML
    public void onClickEnter(KeyEvent e) throws SQLException, ClassNotFoundException, IOException
    {
      if (e.getCode().equals(KeyCode.ENTER)) {
        log();
      } else if ("UP".equals(String.valueOf(e.getCode()))) {
        this.PseudoLogin.requestFocus();
      }
    }
}
