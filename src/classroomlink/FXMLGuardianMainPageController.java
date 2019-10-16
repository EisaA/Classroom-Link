/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

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
public class FXMLGuardianMainPageController implements Initializable {

    /**
     * Initialises the controller class.
     */
    @FXML
    private ListView<String> StudentsList;

    static String chosenStudentID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> items = FXCollections.observableArrayList(); // list of all associated accounts

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
                    items.add(accountElements[1] + " " + accountElements[2] + "                 ID:" + accountElements[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        }

        StudentsList.setItems(items); // display the list on the list view

    }

    @FXML
    private void logout(ActionEvent event) { // run when login clicked
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
        if (StudentsList.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Please Select a Student");
            
            return;
        }
        String[] rowSelected = StudentsList.getSelectionModel().getSelectedItem().split(":");
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

    public ArrayList<String> getStudents() {

        ArrayList<String> associatedID = new ArrayList<String>();

        try {
            FileReader fr = new FileReader("guardianLink.txt");
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

    /*public String getStudentID(String name) {

        try {
            FileReader fr = new FileReader("C:\\Users\\eisaa\\Documents\\Computer Science Year 2\\Software Engineering Group Project\\Prototype\\ClassroomLink\\src\\classroomlink\\guardianLink.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] accountElements;

            while ((line = br.readLine()) != null) {
                accountElements = line.split(",");
                if (chosenStudent.equals(accountElements[1])) {
                    return accountElements[0];
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        }
        return null;
    }*/

}
