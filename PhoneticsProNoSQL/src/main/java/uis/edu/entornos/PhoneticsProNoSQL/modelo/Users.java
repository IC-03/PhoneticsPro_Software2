/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author Carlos
 */


@Document(collection = "Users")
public class Users {
    @Id
    private String id;
    private String email;
    private String password_user;
    private String name_user;

    public Users(String id, String email, String password_user, String name_user) {
        this.id = id;
        this.email = email;
        this.password_user = password_user;
        this.name_user = name_user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordUser() {
        return password_user;
    }

    public void setPasswordUser(String password_user) {
        this.password_user = password_user;
    }

    public String getNameUser() {
        return name_user;
    }

    public void setNameUser(String name_user) {
        this.name_user = name_user;
    }
}