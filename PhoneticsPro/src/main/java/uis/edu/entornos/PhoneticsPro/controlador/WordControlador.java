/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.controlador;

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
import uis.edu.entornos.PhoneticsPro.modelo.Word;
import uis.edu.entornos.PhoneticsPro.servicio.WordServicio;

//Swagger
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Word")
@Api(value = "Registro de las palabras y su transcripcion", tags={"Palabras"})
public class WordControlador {
    
    @Autowired
    WordServicio wordServicio;
    
    //Listar
    @ApiOperation(value = "Listar todas las palabras", response = List.class)
    @GetMapping("/list")
    public List<Word> listarWord(){
        return wordServicio.getWord();
    }
    
    //Buscar por ID
    @ApiOperation(value = "Obtener una palabra por el Id", response = Word.class)
    @GetMapping("/list/{id}")
    public Word buscarWord(@PathVariable Long id){
        return wordServicio.buscarWord(id);
    }
    
    //Crear
    @ApiOperation(value = "Agregar una nueva palabra", response = Word.class)
    @PostMapping("/")
    public ResponseEntity<Word> crearWord(@RequestBody Word word){
        Word obj = wordServicio.nuevoWord(word);
        return new ResponseEntity<>(obj, HttpStatus.OK);
                
    }
    
    //Actualizar
    @ApiOperation(value = "Actualizar una palabra existente", response = Word.class)
    @PutMapping("/")
    public ResponseEntity<Word> actualizar(@RequestBody Word word){
        Word obj = wordServicio.buscarWord(word.getId_word());
        if(obj != null){
            obj.setTerm(word.getTerm());
            obj.setTranscription(word.getTranscription());
            wordServicio.nuevoWord(obj);
        }else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    //Borrar
    @ApiOperation(value = "Borrar una palabra por su ID", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Word> borrar(@PathVariable Long id){
        Word obj = wordServicio.buscarWord(id);
        if(obj != null){
            wordServicio.borrarWord(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
