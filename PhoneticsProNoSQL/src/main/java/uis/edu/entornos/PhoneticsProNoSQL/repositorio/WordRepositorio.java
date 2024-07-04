/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Word;

/**
 *
 * @author Carlos
 */
public interface WordRepositorio extends MongoRepository<Word, String>{
    
}
