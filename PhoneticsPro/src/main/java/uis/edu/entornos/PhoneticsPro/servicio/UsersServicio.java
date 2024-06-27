/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uis.edu.entornos.PhoneticsPro.modelo.LoginDto;
import uis.edu.entornos.PhoneticsPro.modelo.Users;
import uis.edu.entornos.PhoneticsPro.repositorio.UsersRepositorio;


@Service
@Transactional
public class UsersServicio implements IUsersServicio {
    
    @Autowired
    UsersRepositorio usersRepositorio;
    
    
    // Listar 
    @Override
    public List<Users> getUser(){
        return usersRepositorio.findAll();
    }
    
    // Crear
    @Override
    public Users nuevoUser(Users user){
        return usersRepositorio.save(user);
    }
    
    // Buscar
    @Override
    public Users buscarUser(Long id){
        Users user = null;
        user = usersRepositorio.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        return user;
    }
    
    // Borrar
    @Override
    public int borrarUser(Long id){
        usersRepositorio.deleteById(id);
        return 1;
    }
    
    //Login
    public int login(LoginDto userDto){
        int CountUser = usersRepositorio.findByEmailAndPasswordInt(userDto.getEmail(), userDto.getPassword_user());
        return CountUser;
    };
    
    public ResponseEntity<?> ingresar(LoginDto userDto){
        Map<String, Object> response = new HashMap<>();
        Users user= null;
        
        try{
            user = usersRepositorio.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword_user());
            
            if(user == null){
                response.put("Usuario", null);
                response.put("Mensaje", "Alerta: email o password incorrectos");
                response.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("Usuario", user);
                response.put("Mensaje", "Datos correctos");
                response.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            
        }catch(Exception e){
            response.put("Usuario", null);
            response.put("Mensaje", "Ha ocurrido un error");
            response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
