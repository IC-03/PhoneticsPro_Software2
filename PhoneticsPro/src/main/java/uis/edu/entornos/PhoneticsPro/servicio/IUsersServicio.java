/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.List;
import uis.edu.entornos.PhoneticsPro.modelo.Users;


public interface IUsersServicio {
    
    List<Users> getUser();
    
    Users nuevoUser(Users user);
    
    Users buscarUser(Long id);
    
    int borrarUser(Long id);
    
}
