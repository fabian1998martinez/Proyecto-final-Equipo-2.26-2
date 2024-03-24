
package com.redsocial.service;

import com.redsocial.repositorio.RolRepositorio;
import com.redsocial.entidades.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RolService {
    
    @Autowired
    RolRepositorio rolRepositorio;
    
    public List<Rol> listarRol(){
    
      return rolRepositorio.findAll();
    }
}
