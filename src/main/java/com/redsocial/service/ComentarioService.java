
package com.redsocial.Service;
import com.redsocial.entidades.Comentario;
import com.redsocial.entidades.Publicacion;
import com.redsocial.repositorio.ComentarioRepositorio;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ComentarioService {
    
    @Autowired
    ComentarioRepositorio comentarioRepositorio;
    
    @Transactional
    public void registrarComentario(Comentario comentario){
       
       comentarioRepositorio.save(comentario);
       
    }
    
  public Comentario buscarPorId(String id) {
        return comentarioRepositorio.findById(id).orElse(null);
    }
   public List<Comentario> obtenerComentariosPorPublicacion(Publicacion publicacion) {
        return comentarioRepositorio.findByPublicacion(publicacion);
    }
    
    @Transactional
    public void actualizar(String id,String texto){
    
        Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
        
        if(respuesta.isPresent()){
           
            Comentario comentario = respuesta.get();
            
            comentario.setId(id);
            comentario.setTexto(texto);
            
            comentarioRepositorio.save(comentario);
            
        }
    }    
    
    public void darBaja(String id){
        
       Optional<Comentario> respuesta = comentarioRepositorio.findById(id);
       
       if(respuesta != null){
        Comentario comentario = respuesta.get();
        
        comentario.setId(id);
        
        comentarioRepositorio.save(comentario);
       }
       
    }
    
    
    
}
