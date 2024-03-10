
package com.redsocial.Servicio;

import com.redsocial.Repository.RolRepositorio;
import com.redsocial.Repository.UsuarioRepositorio;
import com.redsocial.entidades.Rol;
import com.redsocial.entidades.Usuario;
import com.redsocial.enumercion.TipoRol;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsuarioServicio {
    
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Transactional
    public void crearUsuario(String nombre, String contrasena, String correo, String tipoRol) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setCorreo(correo);

        // Buscar el rol por el nombre
        Optional<Rol> rolOptional = rolRepositorio.findByTipoRol(TipoRol.valueOf(tipoRol.toUpperCase()));

        if (rolOptional.isPresent()) {
            // Si el rol existe, asociarlo al usuario
            nuevoUsuario.setRol(rolOptional.get());
        } else {
            // Si el rol no existe, crearlo y luego asociarlo al usuario
            Rol nuevoRol = new Rol();
            nuevoRol.setTipoRol(TipoRol.valueOf(tipoRol.toUpperCase()));
            rolRepositorio.save(nuevoRol);

            nuevoUsuario.setRol(nuevoRol);
        }

        usuarioRepositorio.save(nuevoUsuario);
    }

    @Transactional
    public void actualizarUsuario(String id, String nombre, String contrasena, String correo, String tipoRol) {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setContrasena(contrasena);
            usuario.setCorreo(correo);

            // Buscar el rol por el nombre
            Optional<Rol> rolOptional = rolRepositorio.findByTipoRol(TipoRol.valueOf(tipoRol.toUpperCase()));


            if (rolOptional.isPresent()) {
                // Si el rol existe, asociarlo al usuario
                usuario.setRol(rolOptional.get());
            } else {
                // Si el rol no existe, crearlo y luego asociarlo al usuario
                Rol nuevoRol = new Rol();
                nuevoRol.setTipoRol(TipoRol.valueOf(tipoRol.toUpperCase()));
                rolRepositorio.save(nuevoRol);

                usuario.setRol(nuevoRol);
            }

            usuarioRepositorio.save(usuario);
        }
    }
    
    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return usuarioRepositorio.findById(id);
    }

    public Set<Usuario> listarUsuarios() {
        return new HashSet<>(usuarioRepositorio.findAll());
    }

    @Transactional
    public void darBajaUsuario(String id) {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        respuesta.ifPresent(usuario -> {
            usuario.setDarBaja(false);
            usuarioRepositorio.save(usuario);
        });
    }
}
