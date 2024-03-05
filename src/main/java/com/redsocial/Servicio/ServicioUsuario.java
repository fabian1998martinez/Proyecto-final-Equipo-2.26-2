
package com.redsocial.Servicio;

import com.redsocial.Repository.UsuarioRepositorio;
import com.redsocial.entidades.Usuario;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioUsuario {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void registrarUsuario(String contrasena,boolean disenador){
        
       Usuario usuario = new Usuario();
       
       usuario.setContraseña(contrasena);
       usuario.setDiseñador(disenador);
       usuario.setDarBaja(true);
       
       usuarioRepositorio.save(usuario);
       
    }
    
    @Transactional
    public void actualizar(String id,String contrasena,boolean disenador){
    
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        if(respuesta.isPresent()){
           
            Usuario usuario = respuesta.get();
            
            usuario.setDiseñador(disenador);
            usuario.setContraseña(contrasena);
            usuario.setDarBaja(true);
            
            usuarioRepositorio.save(usuario);
            
        }
    }
    
    public List<Usuario> listarUsuarios(){
     
        List<Usuario> listaDeUsuarios = new ArrayList();
        
        listaDeUsuarios = usuarioRepositorio.findAll();
        
    
        return listaDeUsuarios;
        
    }
    
    
    public void darBaja(String id){
        
       Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
       
       if(respuesta != null){
        Usuario usuario = respuesta.get();
        
        usuario.setDarBaja(false);
        
        usuarioRepositorio.save(usuario);
       }
       
    }
    
    
    
}
