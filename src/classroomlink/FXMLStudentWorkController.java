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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sajid
 */
public class FXMLStudentWorkController implements Initializable {

    static String chosenID;
    String StudentID;

    @FXML
    private TableView<StudentWork> viewList;

    @FXML
    private TableColumn subject;

    @FXML
    private TableColumn grade;

    @FXML
    private TableColumn title;

    @FXML
    private Button viewComments;

    @FXML
    private Button back;

    @FXML
    void getData(ActionEvent event) {
        StudentWork chosenWork = viewList.getSelectionModel().getSelectedItem(); //stores studentTable object of selected row in test.
        if (chosenWork == null) {
            System.out.println("Click on a piece of work");
            return;
        }

        chosenID = chosenWork.getID();

        System.out.println("Comments");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLComments2.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (User.accountType.equals("Student")) {
            StudentID = User.ID;
            //back.setVisible(false); // if the use is a student then the previous page is the logout
        } else {
            StudentID = FXMLGuardianMainPageController.chosenStudentID;
        }

        //An arraylist with string[] of each line in db.csv
        ArrayList<String[]> a = new ArrayList<String[]>();
        try {
            a = getFile();
        } catch (Exception e) {
        }

        //Creating coloumns for table view
        TableColumn title = new TableColumn("TITLE");
        TableColumn subject = new TableColumn("SUBJECT");
        TableColumn grade = new TableColumn("GRADE");
        //adding coloumns to table view
        viewList.getColumns().addAll(title, subject, grade);
        //creating a list of all data to add to table
        ObservableList<StudentWork> data = FXCollections.observableArrayList();
        //for loop to add students info from csv file
        for (int i = 0; i < a.size(); i++) {
            data.add(new StudentWork(a.get(i)[2], a.get(i)[3], a.get(i)[4], a.get(i)[0])); // pass the work ID as well
        }
        title.setCellValueFactory(new PropertyValueFactory<StudentWork, String>("title"));
        subject.setCellValueFactory(new PropertyValueFactory<StudentWork, String>("subject"));
        grade.setCellValueFactory(new PropertyValueFactory<StudentWork, String>("grade"));

        viewList.setItems(data);
        
    }

    public ArrayList<String[]> getFile() throws IOException {
        ArrayList<String[]> a = new ArrayList<String[]>();
        FileReader freader = new FileReader("StudentWork.txt");
        BufferedReader breader = new BufferedReader(freader);
        String ln = "";
        while ((ln = breader.readLine()) != null) {
            String[] subjects = ln.split(",");
            if (subjects[1].equals(StudentID)) {
                a.add(subjects);
            }

        }
        return a;
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
    private void back(ActionEvent event) {
        System.out.println("logout login");
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

}
