/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.*;
import java.io.*;
import javafx.scene.input.KeyCode;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alex
 */
public class FXMLPrivateMessagesController implements Initializable {

    /**
     * Initialises the controller class.
     */
    //String accountID = "4040";
    String test = "";
    String test2 = "";
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date2 = new Date();
    String date = dateFormat.format(date2);
    DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
    Date date3 = new Date();
    String time = dateFormat2.format(date3);
    //String datetest = "22/3/2018";
    //String timetest = "00:31";
    String toLookFor;

    @FXML
    private TextArea Textarea;

    @FXML
    private TextArea Textarea1;

    @FXML
    private TextField Textfield;

    @FXML
    private TextField Textfield2;

    @FXML
    private TextArea Textarea2;
    
    @FXML
    private ListView<String> contacts;

    @FXML
    private void handleEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
        }
    }

    @FXML
    private void handleRefresh(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLPrivateMessages.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleSubmit(MouseEvent event) throws IOException {
        String test0 = Textfield.getText();
        String recipientID = test0;
        String subject = Textfield2.getText();
        String body = Textarea2.getText();

        try {
            File myFile = new File("messages.txt");
            if (myFile.createNewFile()) {
                System.out.println("DEBUG: No file, created one!");
                try {
                    String filename = "messages.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    System.getProperty("line.separator");
                    fw.write(User.ID + "%" + recipientID + "%" + date + "%" + time + "%" + subject + "%" + body); //appends the string to the file
                    fw.close();
                    Textfield.setText("");
                    Textfield2.setText("");
                    Textarea2.setText("");
                } catch (IOException ioe) {
                }
            } else {
                System.out.println("DEBUG: File already exists, no file created.");
                try {
                    String filename = "messages.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    System.getProperty("line.separator");
                    fw.write("\r\n" + User.ID + "%" + recipientID + "%" + date + "%" + time + "%" + subject + "%" + body);//appends the string to the file
                    fw.close();
                    Textfield.setText("");
                    Textfield2.setText("");
                    Textarea2.setText("");
                } catch (IOException ioe) {
                }
            }
        } catch (IOException e) {
        }
        handleRefresh(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            File myFile = new File("messages.txt");
            if (myFile.createNewFile()) {
                System.out.println("DEBUG: No file, created one!");
                System.out.println("No messages to show");
                try {
                    String filename = "messages.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    fw.write("0000%1111%AAAA%BBBB%CCCC%DDDD");//appends the string to the file
                    fw.close();
                } catch (IOException ioe) {
                }
            } else {
                System.out.println("DEBUG: File already exists, no file created.");
                try {
                    Scanner scanner = new Scanner(myFile);
                    int lineNum = 0;
                    boolean found = false;
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] line2 = line.split("%");
                        String line3 = line2[1];
                        lineNum++;
                        if (line3.equals(User.ID)) {
                            System.out.println("I found it on line " + lineNum);
                            for (int i = 1; i < line2.length; i++) {
                                System.out.println(line2[i]);
                                test = Textarea.getText();
                                Textarea.setText(test + line2[i] + "\r\n");
                                found = true;
                            }
                            test = Textarea.getText();
                            Textarea.setText(test + "\r\n");
                        }
                    }
                    if (found == false) {
                        System.out.println("No messages to show");
                    }
                } catch (FileNotFoundException e) {
                }
            }
        } catch (IOException e) {
        }
        try {
            File myFile = new File("messages.txt");
            if (myFile.createNewFile()) {
                System.out.println("DEBUG: No file, created one!");
                System.out.println("No messages to show");
                try {
                    String filename = "messages.txt";
                    FileWriter fw = new FileWriter(filename, true); //the true will append the new data
                    fw.write("0000%1111%AAAA%BBBB%CCCC%DDDD");//appends the string to the file
                    fw.close();
                } catch (IOException ioe) {
                }
            } else {
                System.out.println("DEBUG: File already exists, no file created.");
                try {
                    Scanner scanner = new Scanner(myFile);
                    int lineNum = 0;
                    boolean found = false;
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] line2 = line.split("%");
                        String line3 = line2[0];
                        lineNum++;
                        if (line3.equals(User.ID)) {
                            System.out.println("I found it on line " + lineNum);
                            for (int i = 1; i < line2.length; i++) {
                                System.out.println(line2[i]);
                                test = Textarea1.getText();
                                Textarea1.setText(test + line2[i] + "\r\n");
                                found = true;
                            }
                            test = Textarea1.getText();
                            Textarea1.setText(test + "\r\n");
                        }
                    }
                    if (found == false) {
                        System.out.println("No messages to show");
                    }
                } catch (FileNotFoundException e) {
                }
            }
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
        
        
        if(User.accountType.equals("Guardian")) { // the parents can send messages to teachers
            toLookFor = "Teacher";
        } else if (User.accountType.equals("Teachers")) { // the teachers can send messages to parents 
            toLookFor = "Guardian";
        } // and students cannot access private messaging        
        
        ObservableList<String> items = FXCollections.observableArrayList(); // list of all associated accounts

        try {
            FileReader fr = new FileReader("allAccounts.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] accountElements;
            //System.out.println("test");
            System.out.println(User.ID);

            while ((line = br.readLine()) != null) {
                accountElements = line.split(",");
                if (accountElements[3].equals(toLookFor)) {
                    items.add(accountElements[1] + " " + accountElements[2] + "                 ID:" + accountElements[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("File problem");
        }

        contacts.setItems(items); // display the list on the list view       
        
    }

    @FXML
    private void logout(ActionEvent event) { // run when login clicked
        System.out.println("logout login");
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
