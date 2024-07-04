/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsProNoSQL.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uis.edu.entornos.PhoneticsProNoSQL.modelo.Users;

/**
 *
 * @author Carlos
 */

public interface UsersRepositorio extends MongoRepository<Users, String> {
    
    @Query("{ 'email' : ?0, 'password_user' : ?1 }")
    Integer countByEmailAndPassword(String email, String passwordUser);

    @Query("{ 'email' : ?0, 'password_user' : ?1 }")
    Users findByEmailAndPassword(String email, String passwordUser);
}
