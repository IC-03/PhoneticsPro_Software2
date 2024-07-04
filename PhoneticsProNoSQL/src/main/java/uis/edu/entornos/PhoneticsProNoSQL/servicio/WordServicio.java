/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Word;
import uis.edu.entornos.PhoneticsProNoSQL.repositorio.WordRepositorio;

/**
 *
 * @author Carlos
 */

@Service
public class WordServicio {
    
    @Autowired
    WordRepositorio wordRepo;
    
    public List<Word> getWords() {
        return wordRepo.findAll();
    }

    public Word getWordById(String id) {
        return wordRepo.findById(id).orElse(null);
    }

    public Word createWord(Word word) {
        return wordRepo.save(word);
    }

    public void deleteWord(String id) {
        wordRepo.deleteById(id);
    }
}