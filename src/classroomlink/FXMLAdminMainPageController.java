/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eisaa
 */
public class FXMLAdminMainPageController implements Initializable {

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    @FXML
    private void logout(ActionEvent event) { // run when login clicked
        System.out.println("admin login");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }
    
    @FXML
    private void addAccount(ActionEvent event) {       
        System.out.println("addaccount");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLAdminAddAccount.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }   
    
    @FXML
    private void editAccount(ActionEvent event) {
        
    }
    @FXML
    private void removeAccount(ActionEvent event) {
        
    }
    @FXML
    private void LinkTeacherStudent(ActionEvent event) {
    
    }
    
    @FXML
    private void LinkGuardianStudent(ActionEvent event) {
    
    }
    
}
