/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

/**
 *
 * @author eisaa
 */
public class User {
    static String ID;
    static String firstName;
    static String lastName;
    static String password;
    static String accountType;
    
    public User (String id, String fname, String sname, String type, String pass) {
        ID = id;
        firstName = fname;
        lastName = sname;
        password = pass;
        accountType = type;
    }
    
    public boolean login (String passwordEntered) {
        if (password.equals(passwordEntered)) { // check if the password is correct
            return true;
        } else {
            return false; // if the password entered is wrong
        }
    }
    
    public static String getID() { // don't need, not private variables
        
        return ID.toString();
    }
}
