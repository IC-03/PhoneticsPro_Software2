/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Attempt;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Users;
import uis.edu.entornos.PhoneticsProNoSQL.repositorio.AttemptRepositorio;
import uis.edu.entornos.PhoneticsProNoSQL.repositorio.UsersRepositorio;

/**
 *
 * @author Carlos
 */
@Service
public class AttemptServicio {
    @Autowired
    AttemptRepositorio attemptRepo;
    
    @Autowired
    UsersRepositorio userRepo;
    
    public List<Attempt> getAttempts() {
        return attemptRepo.findAll();
    }

    public Attempt getAttemptById(String id) {
        return attemptRepo.findById(id).orElse(null);
    }

    public Attempt createAttempt(Attempt attempt) {
        // Asegúrate de que el usuario existe antes de guardar el intento
        Users user = userRepo.findById(attempt.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        attempt.setUser(user);
        return attemptRepo.save(attempt);
    }

    public void deleteAttempt(String id) {
        attemptRepo.deleteById(id);
    }
}
