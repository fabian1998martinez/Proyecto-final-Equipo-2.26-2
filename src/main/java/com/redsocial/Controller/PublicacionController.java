package com.redsocial.controller;

import com.redsocial.entidades.Comentario;
import com.redsocial.entidades.Publicacion;
import com.redsocial.entidades.Usuario;
import com.redsocial.Servicio.PublicacionServicio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionServicio publicacionService;
    
    @GetMapping("/listaPublicaciones")
    public String listarPublicaciones(Model model) {
        model.addAttribute("publicaciones", publicacionService.obtenerTodasPublicaciones());
        return "publicacion";
    }
    
    @GetMapping("/formularioDePublicacion")
    public String formularioDePublicacion(Model model){
          model.addAttribute("nuevaPublicacion", new Publicacion());
         return "crearPublicacion";    
    }
    
    @PostMapping("/crear")
   public String crearPublicacion(@RequestParam("imagen") MultipartFile imagen,
                                   @RequestParam("nombre") String nombre,
                                   @RequestParam("descripcion") String descripcion) {
        try {
            Publicacion nuevaPublicacion = new Publicacion();
            nuevaPublicacion.setNombre(nombre);
            nuevaPublicacion.setDescripcion(descripcion);
            if (!imagen.isEmpty()) {
                nuevaPublicacion.setImagen(imagen.getBytes());
            }
            publicacionService.guardarPublicacion(nuevaPublicacion);
            return "redirect:/listaPublicaciones";
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de error al cargar la imagen
            return "redirect:/crearPublicacion";
        }
    }


   @PostMapping("/publicaciones/{id}/comentarios")
    public String agregarComentario(@PathVariable("id") String id, String texto, @AuthenticationPrincipal Usuario usuario) {
    Publicacion publicacion = publicacionService.buscarPublicacionPorId(id);
    Comentario comentario = new Comentario();
    comentario.setTexto(texto);
    comentario.setUsuario(usuario);
    publicacionService.agregarComentario(publicacion, comentario);
    return "redirect:/publicaciones";
}
}
