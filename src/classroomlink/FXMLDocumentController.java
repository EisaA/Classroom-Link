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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author eisaa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label label;

    @FXML
    private void adminLogin(ActionEvent event) {
        System.out.println("admin login");
        try {
            Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLAdmin.fxml")); // change sceneController
            Scene studentSelectScene = new Scene(studentSelectParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(studentSelectScene);
            window.show();
        } catch (IOException ex) {
            System.out.println("Error Chaning Screen" + ex);
        }
    }

    @FXML
    private void login(ActionEvent event) { // run when login clicked
        if (userNameTextField.getText().equals("")) {
            label.setText("Please enter a username");
            return;
        }
        String[] account = checkAccount(userNameTextField.getText());
        if (account == null) { // If no user with the username was found
            label.setText("The no user was found with that username");
        } else { //username found

            if (passwordTextField.getText().equals("")) { // did the user did not enter a password
                label.setText("Please enter a password");
                return;
            }

            User currentUser = new User(account[0], account[1], account[2], account[3], account[4]); // should not stay like this

            switch (account[3]) { // which account type to create. Consider chnanging the order of this
                case ("Student"):
                    currentUser = new User(account[0], account[1], account[2], account[3], account[4]);
                    break;
                case ("Teacher"):
                    currentUser = new Teacher(account[0], account[1], account[2], account[3], account[4]);
                    break;
                case ("Guardian"):
                    currentUser = new Guardian(account[0], account[1], account[2], account[3], account[4]);
                    break;
                default:
                    //currentUser = new User(account[0],account[1],account[2],account[3],account[4]);
                    System.out.println("Error, user type not recognised");
                    break;
            }

            if (currentUser.login(passwordTextField.getText()) != true) { // if the password is not correct
                label.setText("The password is incorrect");
            } else {
                label.setText("Correct details entered");
                System.out.println("Logged in");

                try {

                    Parent studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); // change sceneController

                    switch (currentUser.accountType) { // which account type to create. Consider chnanging the order of this
                        case ("Student"):
                            System.out.println("student");
                            studentSelectParent = FXMLLoader.load(getClass().getResource("FXMLStudentWork.fxml"));
                            
                            break;
                        case ("Teacher"):
                            System.out.println("teacher");
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
    }

    private String[] checkAccount(String name) { // check if username entered corresponds to an account        
        try {
            FileReader fr = new FileReader("allAccounts.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] accountElements;

            while ((line = br.readLine()) != null) {
                accountElements = line.split(",");
                if (accountElements[1].equals(name)) {
                    //for (int i=0; i<4; i++) { 
                    /*ID = accountElements[0];
                    firstName = accountElements[1];
                    lastName = accountElements[2];
                    accountType = accountElements[3];
                    password = accountElements[4];
                     */
                    return accountElements; // return the elements of this account to check the password
                }
            }
            return null; // if the username is not found            

        } catch (IOException e) {
            System.out.println("File problem");
        }
        return null; // file problem?
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
