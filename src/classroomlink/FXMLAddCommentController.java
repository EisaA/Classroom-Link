/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eisaa
 */
public class FXMLAddCommentController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    public TextArea body;

    @FXML
    public TextField title, Author;

    static String chosenWork = FXMLStudentWorkController.chosenID;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    File file = new File("StudentWork.txt"); // Create file + filename

    public void submitComment(ActionEvent event) throws IOException {
        //String Ttile = title.getText();
        String Bbody = body.getText();
        String Aauthor = Author.getText();  // don't need the ID of the User
        check(file);
        //String complete = Aauthor + "//" + Ttile + "//" + Bbody;
        String complete = Aauthor + "//" + Bbody;
        Write(file, "---" + complete);
        gotoComments(event);
    }

    /*public void Previous(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLComment2.fxml"));
        root.getChildren().setAll(pane);
    }*/
    @FXML
    private void previous(ActionEvent event) { // run when login clicked
        gotoComments(event);
    }

    public void gotoComments(ActionEvent event) {
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

    private static void check(File f) { //checks file exists
        if (f.exists()) {
            System.out.println("exists");
        } else {
            try {
                f.createNewFile();
                System.out.println("file created");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void Write(File f, String data) {
        String oldComments = "";
        try {
            oldComments = removeOldText();
            //oldComments= oldText.toString();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddCommentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String content = oldComments + data;
            fw = new FileWriter(f, true);
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

    public static String removeOldText() throws IOException { // replace the line when a new string is added

        File inputFile = new File("StudentWork.txt");
        File tempFile = new File("StudentWorkTemp.txt");
        String removedText = "";

        BufferedReader r = new BufferedReader(new FileReader(inputFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while ((currentLine = r.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            String[] elements = trimmedLine.split(",");
            if (elements[0].equals(chosenWork)) {
                removedText = trimmedLine;
                continue; // don't rewrite this line
            }
            w.write(currentLine + System.getProperty("line.separator"));
        }
        w.close();
        r.close();
        boolean successful = tempFile.renameTo(inputFile); // remane the file to replace the other
        return removedText;
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
}
