/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.List;
import uis.edu.entornos.PhoneticsPro.modelo.Attempt;


public interface IAttemptServicio {
    
    List<Attempt> getAttempt();
    
    Attempt nuevoAttempt(Attempt attempt);
    
    Attempt buscarAttempt(Long id);
    
    int borrarAttempt(Long id);
    
}
