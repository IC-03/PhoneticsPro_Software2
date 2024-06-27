/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uis.edu.entornos.PhoneticsPro.modelo.Users;



public interface UsersRepositorio extends JpaRepository<Users, Long>{
    
    @Query("select count(*) from Users as p where p.email = :email and p.password_user= :password_user")
    Integer findByEmailAndPasswordInt(@Param("email") String email, @Param("password_user") String password_user);
    
    @Query("select p from Users as p where p.email = :email and p.password_user= :password_user")
    Users findByEmailAndPassword(@Param("email") String email, @Param("password_user") String password_user);
}


