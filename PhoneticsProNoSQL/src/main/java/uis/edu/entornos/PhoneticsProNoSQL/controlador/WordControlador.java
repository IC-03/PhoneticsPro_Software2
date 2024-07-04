/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Word;
import uis.edu.entornos.PhoneticsProNoSQL.servicio.WordServicio;

/**
 *
 * @author Carlos
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Word")
public class WordControlador {
    
    @Autowired
    WordServicio wordServicio;
    
    //Listar
    @GetMapping("/list")
    public List<Word> listarWord(){
        return wordServicio.getWords();
    }
    
    //Buscar por ID
    @GetMapping("/list/{id}")
    public Word buscarWord(@PathVariable String id){
        return wordServicio.getWordById(id);
    }
    
    //Crear
    @PostMapping("/")
    public ResponseEntity<Word> crearWord(@RequestBody Word word){
        Word obj = wordServicio.createWord(word);
        return new ResponseEntity<>(obj, HttpStatus.OK);
                
    }
    
    //Actualizar
    @PutMapping("/")
    public ResponseEntity<Word> actualizar(@RequestBody Word word){
        Word obj = wordServicio.getWordById(word.getId());
        if(obj != null){
            obj.setTerm(word.getTerm());
            obj.setTranscription(word.getTranscription());
            wordServicio.createWord(obj);
        }else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Word> borrar(@PathVariable String id){
        Word obj = wordServicio.getWordById(id);
        if(obj != null){
            wordServicio.deleteWord(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}

