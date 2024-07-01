/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = Users.TABLE_NAME)
public class Users {
    public static final String TABLE_NAME="users";
    
    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password_user")
    private String password_user;

    @Column(name = "name_user")
    private String name_user;
    // Constructores 

    public Users() {
    }

    public Users(Long id_user, String email, String password_user, String name_user) {
        this.id_user = id_user;
        this.email = email;
        this.password_user = password_user;
        this.name_user = name_user;
    }

    
    // Setters y Getters

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

}
