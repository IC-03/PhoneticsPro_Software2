/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.LoginDto;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Users;
import uis.edu.entornos.PhoneticsProNoSQL.servicio.UsersServicio;
/**
 *
 * @author Carlos
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/Users")
public class UsersControlador {
    
    @Autowired
    UsersServicio usersServicio;
    
    //Listar
    @GetMapping("/list")
    public List<Users> listarUsers(){
        return usersServicio.getUsers();
    }
    
    //Buscar por ID
    @GetMapping("/list/{id}")
    public Users buscarPorId(@PathVariable String id){
        return usersServicio.getUserById(id);
    }
    
    //Crear
    @PostMapping("/")
    public ResponseEntity<Users> agregar(@RequestBody Users user){
        Users obj = usersServicio.createUser(user);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Actualizar
    @PutMapping("/")
    public ResponseEntity<Users> actualizar(@RequestBody Users user){
        Users obj = usersServicio.getUserById(user.getId());
        if(obj != null){
            obj.setEmail(user.getEmail());
            obj.setPasswordUser(user.getPasswordUser());
            obj.setNameUser(user.getNameUser());
            usersServicio.createUser(obj);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }  
    
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> borrar(@PathVariable String id){
        Users obj = usersServicio.getUserById(id);
        if(obj != null){
            usersServicio.deleteUser(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Validar Login
    @PostMapping("/loginclient")
    public int login(@RequestBody LoginDto user){
        return usersServicio.login(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto user){
        return usersServicio.ingresar(user);
    }
}
