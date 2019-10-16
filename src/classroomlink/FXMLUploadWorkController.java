/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eisaa
 */
public class FXMLUploadWorkController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField grade;

    @FXML
    private TextField comment;
    
    @FXML
    private TextField subject;

    @FXML
    private ListView<String> StudentsList;

    File file = new File("StudentWork.txt"); // Create file + filename

    /**
     * Initialises the controller class.
     */
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
            System.out.println("File problem" + e);
        }

        StudentsList.setItems(items); // display the list on the list view

    }

    @FXML
    private void submit(ActionEvent event) { // run when submit clicked

        String chosenWork = StudentsList.getSelectionModel().getSelectedItem(); //stores studentTable object of selected row in test.
        String[] elements = chosenWork.split(":");
        String chosenID = elements[1];

        int nextID = Integer.parseInt(getLastID());
        nextID++;


        String content = nextID + "," + chosenID + "," + title.getText() + "," + subject.getText() + "," + grade.getText() + ",---" + User.lastName + "//" + comment.getText() + "\n";

        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            //String content = data;
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("changes have been made");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getLastID() {
        String[] lastLine = new String[5];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            

            while ((line = reader.readLine()) != null) {
                lastLine = line.split(",");
            }
        } catch (FileNotFoundException e) {

        } catch (IOException ex) {
            Logger.getLogger(FXMLUploadWorkController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastLine[0]; // return the last ID
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
    private void back(ActionEvent event) {
        System.out.println("back");
        try {

            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLTeacherMainPage.fxml")); // change sceneController

            switch (User.accountType) { // which account type to create. Consider chnanging the order of this
                case ("Student"):
                    studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLStudentMainPage.fxml"));
                    break;
                case ("Teacher"):
                    studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLTeacherMainPage.fxml"));
                    break;
                case ("Guardian"):
                    studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLGuardianMainPage.fxml"));
                    break;
                default:
                    System.out.println("Error, user type not recognised");
                    break;
            }

            Scene studentSelectScene = new Scene(studentSelectParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(studentSelectScene);
            window.show();

        } catch (IOException ex) {
            //Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error Chaning Screen" + ex);
        }
    }

    public ArrayList<String> getStudents() { // all teacher assocaited

        ArrayList<String> associatedID = new ArrayList<String>();

        try {
            FileReader fr = new FileReader("teacherLink.txt");
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
            System.out.println("File problem" + e);
        }

        return associatedID;
    }

}
