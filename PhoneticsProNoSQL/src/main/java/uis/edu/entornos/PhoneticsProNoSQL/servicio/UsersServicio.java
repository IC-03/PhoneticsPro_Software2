/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.servicio;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.LoginDto;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Users;
import uis.edu.entornos.PhoneticsProNoSQL.repositorio.UsersRepositorio;

/**
 *
 * @author Carlos
 */


@Service
public class UsersServicio {
    @Autowired
    private UsersRepositorio userRepositorio;

    public List<Users> getUsers() {
        return userRepositorio.findAll();
    }

    public Users getUserById(String id) {
        return userRepositorio.findById(id).orElse(null);
    }

    public Users createUser(Users user) {
        return userRepositorio.save(user);
    }

    public void deleteUser(String id) {
        userRepositorio.deleteById(id);
    }
    
    //Login
    public int login(LoginDto userDto){
        int CountUser = userRepositorio.countByEmailAndPassword(userDto.getEmail(), userDto.getPassword_user());
        return CountUser;
    };
    
    public ResponseEntity<?> ingresar(LoginDto userDto){
        Map<String, Object> response = new HashMap<>();
        Users user= null;
        
        try{
            user = userRepositorio.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword_user());
            
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