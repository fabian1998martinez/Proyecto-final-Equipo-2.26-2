/*
Juan Ignacio: Checkeo Entidad Usuario con boolean de diseñador por simplicidad
 */
package com.redsocial.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    @GenericGenerator(name="uuid",strategy="uuid2" )
    private String Id;
    
    private String contraseña;
    private boolean diseñador;
    
    public Usuario(){
    }
}
