
package com.redsocial.service;

import com.redsocial.repositorio.PublicacionRepositorio;
import com.redsocial.entidades.Comentario;
import com.redsocial.entidades.Publicacion;
import com.redsocial.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublicacionService {
    
    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    
   
    public Publicacion guardarPublicacion(Publicacion publicacion){
        return publicacionRepositorio.save(publicacion);
    }
     public List<Publicacion> obtenerPublicacionesPorMeGusta() {
        // Obtener las publicaciones ordenadas por la cantidad de "me gusta" de mayor a menor
        return publicacionRepositorio.findAllByOrderByCantidadMeGustaDesc();
    }
    
   
    public List<Publicacion> obtenerTodasPublicaciones() {
        return publicacionRepositorio.findAll();
    }
    
   
    public Publicacion buscarPublicacionPorId(String id) {
        
        Publicacion respuesta = publicacionRepositorio.obtenerPorId(id);
        
        return respuesta; 
    }
    public void eliminarPublicacionPorId(String id) {
        publicacionRepositorio.deleteById(id);
    }
    
    
    public void agregarComentario(Publicacion publicacion, Comentario comentario) {
        publicacion.getComentarios().add(comentario);
        comentario.setPublicacion(publicacion);
        publicacionRepositorio.save(publicacion);
    }
    
    
    public List<Publicacion> obtenerPublicacionesPorUsuario(Usuario usuario) {
        return publicacionRepositorio.findByUsuario(usuario);
    }
    
    public void actualizarCantidadMeGusta(Publicacion publicacion, int cantidad) {
        publicacion.setCantidadMeGusta(publicacion.getCantidadMeGusta() + cantidad);
        publicacionRepositorio.save(publicacion);
    }
    

    
}

