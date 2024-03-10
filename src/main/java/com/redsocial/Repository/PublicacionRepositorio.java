
package com.redsocial.Repository;

import com.redsocial.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {
    
}
