/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroomlink;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author sajid
 */
public class StudentWork {
    private SimpleStringProperty ID;
    private SimpleStringProperty title;
    private SimpleStringProperty subject;
    private SimpleStringProperty grade;
    
    StudentWork(String title, String subject, String grade, String ID){
        
        this.title = new SimpleStringProperty(title);
        this.subject = new SimpleStringProperty(subject);
        this.grade = new SimpleStringProperty(grade);
        this.ID = new SimpleStringProperty(ID);
    }
    
    public String getTitle(){
        return title.get();
    }
    public String getSubject(){
        return subject.get();
    }
    public String getGrade(){
        return grade.get();
    }
    public String getID(){
        return ID.get();
    }
    
    public void setTitle(String title){
        this.title.set(title);
    }
    public void setSubject(String subject){
        this.subject.set(subject);
    }
    public void setGrade(String grade){
        this.grade.set(grade);
    }
}
