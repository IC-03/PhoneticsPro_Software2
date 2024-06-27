/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.servicio;

import java.util.List;
import uis.edu.entornos.PhoneticsPro.modelo.Word;



public interface IWordServicio{
    
    List<Word> getWord();
    
    Word nuevoWord(Word word);
    
    Word buscarWord(Long id);
    
    int borrarWord(Long id);
    
}
