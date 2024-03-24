package com.redsocial.controlador;


import com.redsocial.entidades.Comentario;
import com.redsocial.entidades.Publicacion;
import com.redsocial.entidades.Usuario;
import com.redsocial.service.PublicacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;



@Controller
@RequestMapping("/Publicaciones")
public class PublicacionControlador {

    @Autowired
    private PublicacionService publicacionService;
    
    @GetMapping("/listaPublicaciones")
    public String listarPublicaciones(Model model) {
    List<Publicacion> publicaciones = publicacionService.obtenerTodasPublicaciones();
     
    
    model.addAttribute("publicaciones", publicaciones);
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
                                   @RequestParam("descripcion") String descripcion) throws IOException {
            // Crear una nueva instancia de Publicacion
            Publicacion nuevaPublicacion = new Publicacion();
            nuevaPublicacion.setNombre(nombre);
            nuevaPublicacion.setDescripcion(descripcion);


        if (!imagen.isEmpty()) {
            Path ruta = Paths.get( "src//main//resources//static/img");
               try {
                 byte[] bytes = imagen.getBytes();
                 Path rutaAbsoluta = Paths.get(ruta+"//"+imagen.getOriginalFilename());
                 Files.write(rutaAbsoluta, bytes);
                 nuevaPublicacion.setImagen(imagen.getOriginalFilename());
            
        } catch (IOException e) {
            e.printStackTrace();
         
            return "redirect:/error";
        }
        }
      
            publicacionService.guardarPublicacion(nuevaPublicacion);

         
            return "redirect:/Publicaciones/listaPublicaciones";
    
    }
     @DeleteMapping("/{id}")
    public String eliminarPublicacion(@PathVariable String id) {
        // Lógica para eliminar la publicación de la base de datos
        publicacionService.eliminarPublicacionPorId(id);
        // Redirige a la página de lista de publicaciones
        return "redirect:/Publicaciones/listaPublicaciones";
    }


   @PostMapping("/publicaciones/{id}/comentarios")
    public String agregarComentario(@PathVariable("id") String id, String texto,  Usuario usuario) {
    Publicacion publicacion = publicacionService.buscarPublicacionPorId(id);
    Comentario comentario = new Comentario();
    comentario.setTexto(texto);
    comentario.setUsuario(usuario);
    publicacionService.agregarComentario(publicacion, comentario);
    return "redirect:/publicaciones";
}
}
