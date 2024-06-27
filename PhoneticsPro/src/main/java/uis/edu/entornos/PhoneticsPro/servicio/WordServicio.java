/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.entornos.PhoneticsPro.modelo.Word;
import uis.edu.entornos.PhoneticsPro.repositorio.WordRepositorio;



@Service
@Transactional
public class WordServicio implements IWordServicio {

    @Autowired
    WordRepositorio wordRepositorio;
    
    //Listar
    @Override
    public List<Word> getWord() {
        return wordRepositorio.findAll();
    }

    //Crear
    @Override
    public Word nuevoWord(Word word) {
        return wordRepositorio.save(word);
    }

    //Buscar
    @Override
    public Word buscarWord(Long id) {
        Word word = null;
        word = wordRepositorio.findById(id).orElse(null);
        if(word == null){
            return null;
        }
        return word;
    }

    //Borrar
    @Override
    public int borrarWord(Long id) {
        wordRepositorio.deleteById(id);
        return 1;
    }
    
}
