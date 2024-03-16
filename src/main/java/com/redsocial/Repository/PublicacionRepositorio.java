
package com.redsocial.Repository;

import com.redsocial.entidades.Publicacion;
import com.redsocial.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String> {

    public List<Publicacion> findByUsuario(Usuario usuario);
    
}
