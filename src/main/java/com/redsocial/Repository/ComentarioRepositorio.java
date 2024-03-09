
package com.redsocial.Repository;

import com.redsocial.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioRepositorio extends JpaRepository<Comentario, String>{
    
}
