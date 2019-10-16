/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.io.BufferedReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FXMLCommentsController implements Initializable {

    @FXML
    private ListView<String> Comments;

    //private Scanner x;
    File file = new File("StudentWork.txt"); // Create file + filename

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> items = FXCollections.observableArrayList(); // list of all associated accounts

        //ArrayList<String> associatedID = getStudents();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] workElements;
            String[] commentsElements;
            //System.out.println("test");

            while ((line = br.readLine()) != null) {
                workElements = line.split("---");
                if (workElements.length > 1) { // if there are comments
                    for (int i = 1; i < workElements.length; i++) {
                        commentsElements = workElements[i].split("\\");
                        items.add(commentsElements[0] + " " + commentsElements[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        }

        Comments.setItems(items); // display the list on the list view

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
}
