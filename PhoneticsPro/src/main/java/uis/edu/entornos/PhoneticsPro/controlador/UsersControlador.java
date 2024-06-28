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
import uis.edu.entornos.PhoneticsPro.modelo.LoginDto;
import uis.edu.entornos.PhoneticsPro.modelo.Users;
import uis.edu.entornos.PhoneticsPro.servicio.UsersServicio;

//Swagger
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/Users")
@Api(value = "Users API", tags = {"Usuarios"}) //swagger
public class UsersControlador {
    
    @Autowired
    UsersServicio usersServicio;
    
    //Listar
    @ApiOperation(value = "Listar todos los usuarios", response = List.class) //swagger
    @GetMapping("/list")
    public List<Users> listarUsers(){
        return usersServicio.getUser();
    }
    
    //Buscar por ID
    @ApiOperation(value = "Buscar usuario por ID", response = Users.class)
    @GetMapping("/list/{id}")
    public Users buscarPorId(@PathVariable Long id){
        return usersServicio.buscarUser(id);
    }
    
    //Crear
    @ApiOperation(value = "Crear un nuevo usuario", response = Users.class)
    @PostMapping("/")
    public ResponseEntity<Users> agregar(@RequestBody Users user){
        Users obj = usersServicio.nuevoUser(user);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    
    //Actualizar
    @ApiOperation(value = "Actualizar un usuario existente", response = Users.class)
    @PutMapping("/")
    public ResponseEntity<Users> actualizar(@RequestBody Users user){
        Users obj = usersServicio.buscarUser(user.getId_user());
        if(obj != null){
            obj.setEmail(user.getEmail());
            obj.setPassword_user(user.getPassword_user());
            obj.setName_user(user.getName_user());
            usersServicio.nuevoUser(obj);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    
    
    //Borrar
    @ApiOperation(value = "Borrar un usuario por ID", response = Users.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Users> borrar(@PathVariable Long id){
        Users obj = usersServicio.buscarUser(id);
        if(obj != null){
            usersServicio.borrarUser(id);
        } else{
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    
    //Validar Login
    @ApiOperation(value = "Validar login de cliente", response = Integer.class)
    @PostMapping("/loginclient")
    public int login(@RequestBody LoginDto user){
        return usersServicio.login(user);
    }
    
    @ApiOperation(value = "Login de usuario", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto user){
        return usersServicio.ingresar(user);
    }
}
