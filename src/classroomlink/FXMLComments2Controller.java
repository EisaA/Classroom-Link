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
public class FXMLComments2Controller implements Initializable {

    @FXML
    private ListView<String> comments;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList(); // list of all associated accounts

        try {
            FileReader fr = new FileReader("StudentWork.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] workElements;
            String[] commentsElements;
            String[] workDetails;
            //System.out.println("test");

            while ((line = br.readLine()) != null) {
                workElements = line.split("---");
                if (workElements.length > 1) { // if there are comments
                    workDetails = workElements[0].split(",");
                    if (workDetails[0].equals(FXMLStudentWorkController.chosenID)) {
                        for (int i = 1; i < workElements.length; i++) {
                            commentsElements = workElements[i].split("//");
                            items.add(commentsElements[0] + " commented:  " + commentsElements[1]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        }

        comments.setItems(items); // display the list on the list view

    }

    @FXML
    private void logout(ActionEvent event) { // run when login clicked
        System.out.println("Logout");
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
    private void back(ActionEvent event) { // run when login clicked
        System.out.println("back");
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
    private void addComment(ActionEvent event) { // run when login clicked
        
        System.out.println("add comment");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLAddComment.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }
}
