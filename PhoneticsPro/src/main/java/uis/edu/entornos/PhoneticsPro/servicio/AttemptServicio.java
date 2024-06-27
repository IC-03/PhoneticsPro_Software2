/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entornos.PhoneticsPro.modelo.Attempt;
import uis.edu.entornos.PhoneticsPro.repositorio.AttemptRepositorio;


@Service
@Transactional
public class AttemptServicio implements IAttemptServicio {

    @Autowired
    AttemptRepositorio attemptRepositorio;
    
    
    //Listar
    @Override
    public List<Attempt> getAttempt() {
        return attemptRepositorio.findAll();
    }

    //Crear
    @Override
    public Attempt nuevoAttempt(Attempt attempt) {
        return attemptRepositorio.save(attempt);
    }

    //Buscar
    @Override
    public Attempt buscarAttempt(Long id) {
        Attempt attempt = null;
        attempt = attemptRepositorio.findById(id).orElse(null);
        if(attempt == null){
            return null;
        }
        return attempt;
    }

    //Borrar
    @Override
    public int borrarAttempt(Long id) {
        attemptRepositorio.deleteById(id);
        return 1;
    }
    
}
