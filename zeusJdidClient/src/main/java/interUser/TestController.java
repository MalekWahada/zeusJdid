/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interUser;

import java.net.URL;
import java.util.ResourceBundle;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class TestController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       nom.setText(AcceuilController.usconnected.getFirstname());
       prenom.setText(AcceuilController.usconnected.getLastname());
       
    }    
    
}
