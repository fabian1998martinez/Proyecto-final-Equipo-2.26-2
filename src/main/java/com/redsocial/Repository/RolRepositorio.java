
package com.redsocial.Repository;

import com.redsocial.entidades.Rol;
import com.redsocial.enumercion.TipoRol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepositorio extends JpaRepository<Rol, String> {

    public Optional<Rol> findByTipoRol(TipoRol tipoRol);

   
}
