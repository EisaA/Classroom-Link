/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eisaa
 */
public class FXMLAdminAddAccountController implements Initializable {

    /**
     * Initialises the controller class.
     */
    
    File file = new File("allAccounts.txt");
    
     @FXML
    private Label title;
    
    @FXML
    private Label IDlab;
    
    @FXML
    private Label firstnamelab;
    
    @FXML
    private Label Lastnamelab;
    
    @FXML
    private Label Otherlab;
    
    @FXML
    private ChoiceBox<String> type = new ChoiceBox<>();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    @FXML
    private TextField ID;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField lastName;
    
    @FXML
    private TextField Other;
    
    @FXML
    private Button next;
    String TYPE;
    
        @FXML
    private Button submit;
    @FXML
    public void Next(ActionEvent e)throws IOException
    {
        IDlab.setText("ID:");
        title.setText("Please enter the account details");
        type.setVisible(false);
        firstnamelab.setVisible(true);
        Lastnamelab.setVisible(true);
        Otherlab.setVisible(true);
        ID.setVisible(true);
        firstName.setVisible(true);
        lastName.setVisible(true);
        Other.setVisible(true);
        /*if((type.getSelectionModel().isSelected(0))){
            Otherlab.setText("Year:");
            TYPE = ("Student");
        }else if((type.getSelectionModel().isSelected(1))){
            Otherlab.setText("Password");
            TYPE = "Parent";
        }else if((type.getSelectionModel().isSelected(2))){
            Otherlab.setText("Subject:");
            TYPE = "Teacher";
        }*/
        submit.setVisible(true);
        next.setVisible(false);
    }
    
    //Administrator admin = new Administrator();
    @FXML
    public void Submit(ActionEvent e) throws IOException{
        //try{admin.addAccount(ID.getText(),TYPE,firstName.getText(),lastName.getText(),Other.getText());
        //    System.out.println("account added");
        //}catch(IllegalArgumentException f){
        //    System.out.println("Could not add account");
        //}
        
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String content = ID.getText() + "," + firstName.getText() +"," + lastName.getText() + "," + TYPE + "," +Other.getText() + "\n";
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("changes have been made");
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
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
        
        Parent addAccountParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene addAccountScene = new Scene(addAccountParent);
        
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        
        window.setScene(addAccountScene);
        window.show();
    }
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().add("Student");
        type.getItems().add("Parent");
        type.getItems().add("Teacher");
    }      
    
}
