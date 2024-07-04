/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.modelo;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Carlos
 */

@Document(collection = "Attempt")
public class Attempt {
    
    @Id
    private String id;
    private int total_attempt;
    private int correct_attempt;
    private Date date_attempt;
    
    @DBRef
    private Users user;

    public Attempt(String id, int total_attempt, int correct_attempt, Date date_attempt, Users user) {
        this.id = id;
        this.total_attempt = total_attempt;
        this.correct_attempt = correct_attempt;
        this.date_attempt = date_attempt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal_attempt() {
        return total_attempt;
    }

    public void setTotal_attempt(int total_attempt) {
        this.total_attempt = total_attempt;
    }

    public int getCorrect_attempt() {
        return correct_attempt;
    }

    public void setCorrect_attempt(int correct_attempt) {
        this.correct_attempt = correct_attempt;
    }

    public Date getDate_attempt() {
        return date_attempt;
    }

    public void setDate_attempt(Date date_attempt) {
        this.date_attempt = date_attempt;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    
    
}
