
package com.redsocial.Servicio;

import com.redsocial.Repository.ComentarioRepositorio;
import com.redsocial.entidades.Comentario;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ServicioComentario {
    
    @Autowired
    ComentarioRepositorio comentarioRepositorio;
    
    @Transactional
    public void registrarComentario(String id,String texto){
        
       Comentario comentario = new Comentario();
       
       comentario.;
       comentario.setTexto(texto);
       
       comentarioRepositorio.save(comentario);
       
    }
    
    @Transactional
    public void actualizar(String id,String texto){
    
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        
        if(respuesta.isPresent()){
           
            Comentario comentario = respuesta.get();
            
            comentario.(texto);
            comentario.setDarBaja(true);
            
            comentarioRepositorio.save(comentario);
            
        }
    }    
    
    public void darBaja(String id){
        
       Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
       
       if(respuesta != null){
        Comentario comentario = respuesta.get();
        
        comentario.setDarBaja(false);
        
        comentarioRepositorio.save(comentario);
       }
       
    }
    
    
    
}
