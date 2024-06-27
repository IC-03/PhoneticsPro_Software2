/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.entornos.PhoneticsPro.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = Word.TABLE_NAME)
public class Word {
    public static final String TABLE_NAME="Word";
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_word;
    
    @Column(name = "term")
    private String term;
    
    @Column(name = "transcription")
    private String transcription;
    
    
    //Constructores
    public Word() {
    }
    
    public Word(Long id_word, String term, String transcription) {
        this.id_word = id_word;
        this.term = term;
        this.transcription = transcription;
    }
    
    
    //Setters y Getters

    public Long getId_word() {
        return id_word;
    }

    public void setId_word(Long id_word) {
        this.id_word = id_word;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
    
}
