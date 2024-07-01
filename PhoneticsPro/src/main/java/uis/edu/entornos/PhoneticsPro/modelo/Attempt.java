/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = Attempt.TABLE_NAME)
public class Attempt {
    public static final String TABLE_NAME="attempt";
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_attempt;
    
    @Column(name = "total_attempt")
    private int total_attempt;
    
    @Column(name = "correct_attempt")
    private int correct_attempt;
    
    @Column(name = "date_attempt")
    private Date date_attempt;
    

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users id_user;
    
    //Constructores
    public Attempt() {
    }
    
    public Attempt(Long id_attempt, int total_attempt, int correct_attempt, Date date_attempt, Users id_user) {
        this.id_attempt = id_attempt;
        this.total_attempt = total_attempt;
        this.correct_attempt = correct_attempt;
        this.date_attempt = date_attempt;
        this.id_user = id_user;
    }
    
    
    //Setters y Getters

    public Long getId_attempt() {
        return id_attempt;
    }

    public void setId_attempt(Long id_attempt) {
        this.id_attempt = id_attempt;
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

    public Users getId_user() {
        return id_user;
    }

    public void setId_user(Users id_user) {
        this.id_user = id_user;
    }
    
}
