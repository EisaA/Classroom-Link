/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import static classroomlink.FXMLGuardianMainPageController.chosenStudentID;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author eisaa
 */
public class FXMLTeacherMainPageController implements Initializable {
    
    @FXML
    private ListView<String> StudentsListT;
    

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> data = FXCollections.observableArrayList(); // list of all associated accounts
        
        ArrayList<String> associatedID = getStudents();

        try {
            FileReader fr = new FileReader("allAccounts.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] accountElements;
            //System.out.println("test");
            System.out.println(User.ID);

            while ((line = br.readLine()) != null) {
                accountElements = line.split(",");
                if (associatedID.contains(accountElements[0])) {
                    data.add(accountElements[1] + " " + accountElements[2] + "                 ID:" + accountElements[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        } 
        
        StudentsListT.setItems(data); // display the list on the list view
        
    }
    
    @FXML
    private void logout(ActionEvent event) { // run when logout clicked
        System.out.println("logout");
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
    private void privateMessaging(ActionEvent event) {
        System.out.println("PM");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLPrivateMessages.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }

    @FXML
    private void viewStudentsWork(ActionEvent event) {
        if (StudentsListT.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Please Select a Student");
            
            return;
        }
        String[] rowSelected = StudentsListT.getSelectionModel().getSelectedItem().split(":");
        chosenStudentID = rowSelected[1];
        System.out.println(chosenStudentID);
        
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLStudentWork.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }
    
    @FXML
    private void uplaodWork(ActionEvent event) {
        System.out.println("VW");    
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLUploadWork.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }        
        
        
    }
    
    public ArrayList<String> getStudents() {

        ArrayList<String> associatedID = new ArrayList<String>();

        try {
            FileReader fr = new FileReader("teacherLink.txt"); // all teacher associated
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] accountElements;
            //System.out.println("test");
            System.out.println(User.ID);

            while ((line = br.readLine()) != null) {
                accountElements = line.split(",");
                if (User.ID.equals(accountElements[0])) {
                    for (int i = 1; i < accountElements.length; i++) {
                        associatedID.add(accountElements[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        } 

        return associatedID;

    }
    
}
