
package com.redsocial.Controller;


import com.redsocial.Servicio.UsuarioServicio;
import com.redsocial.entidades.Usuario;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
     @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("nuevoUsuario", new Usuario());
        return "Formulario";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
                               @RequestParam("tipoRol") String tipoRol) {
        usuarioServicio.crearUsuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getContrasena(),
                nuevoUsuario.getCorreo(),
                tipoRol
        );
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        Set<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "listado";
    }


}