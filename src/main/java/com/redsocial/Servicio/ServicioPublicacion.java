
package com.redsocial.Servicio;

import com.redsocial.Repository.PublicacionRepositorio;
import com.redsocial.Repository.UsuarioRepositorio;
import com.redsocial.entidades.Publicacion;
import com.redsocial.entidades.Usuario;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioPublicacion {
    
    @Autowired
    PublicacionRepositorio publicacionRepositorio;
    
    @Transactional
    public void registrarPublicacion(String titulo,boolean cuerpo){
        
       Publicacion publicacion = new Publicacion();
       
       publicacion.setTitulo(titulo);
       publicacion.setCuerpo(cuerpo);
       publicacion.setDarBaja(true);
       
       publicacionRepositorio.save(publicacion);
       
    }
    
    @Transactional
    public void actualizar(String id,String titulo,boolean cuerpo){
    
        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
        
        if(respuesta.isPresent()){
           
            Publicacion publicacion = respuesta.get();
            
            publicacion.setCuerpo(cuerpo);
            publicacion.setTitulo(titulo);
            publicacion.setDarBaja(true);
            
            publicacionRepositorio.save(publicacion);
            
        }
    }
    
    public List<Publicacion> listarPublicaciones(){
     
        List<Publicacion> listaDePublicaciones = new ArrayList();
        
        listaDePublicaciones = publicacionRepositorio.findAll();
        
    
        return listaDePublicaciones;
        
    }
    
    
    public void darBaja(String id){
        
       Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);
       
       if(respuesta != null){
        Publicacion publicacion = respuesta.get();
        
        publicacion.setDarBaja(false);
        
        publicacionRepositorio.save(publicacion);
       }
       
    }
    
    
    
}
