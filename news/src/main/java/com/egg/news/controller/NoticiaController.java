/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controller;

import com.egg.news.entity.Noticia;
import com.egg.news.exception.NewsException;
import com.egg.news.service.NoticiaService;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Juan Cruz
 */
@Controller
@RequestMapping("/noticia")

public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/registrar")

    public String registrar() {

        return "crear_noticia.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, @RequestParam("file") MultipartFile imagen) throws NewsException {

        if (!imagen.isEmpty()) {

            Path directorioImagenes = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {

                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                noticiaService.crearNoticia(titulo, cuerpo, imagen.getOriginalFilename());

            } catch (IOException ex) {
            }

        }

        return "crear_noticia.html";

    }

    @GetMapping("/lista_noticias")
    public String listarNoticia(ModelMap modelo) throws NewsException {

        List<Noticia> noticias = noticiaService.listarNoticias();

        if (!noticias.isEmpty()) {

            modelo.addAttribute("noticias", noticias);

        } else {

            modelo.put("vacio", "¡Aún no hay noticias para mostrar!");

        }

        return "listar_noticias.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable String id, ModelMap modelo) {

        noticiaService.eliminarNoticia(id);

        return "redirect:../lista_noticias";

    }

    @GetMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, ModelMap modelo) {

        modelo.put("noticia", noticiaService.getOne(id));

        return "crear_noticia.html";

    }

    @PostMapping("/modificacion/{id}")
    public String modificarNoticia(@PathVariable String id, ModelMap modelo, @RequestParam String titulo, @RequestParam String cuerpo, @RequestParam("file") MultipartFile imagen, @RequestParam String nombreImagen) throws NewsException {

        String imagenFileName = null;

        if (nombreImagen != null) {

            imagenFileName = nombreImagen;

        }

        if (!imagen.isEmpty()) {

            Path directorioImagenes = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {

                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                imagenFileName = imagen.getOriginalFilename();

                noticiaService.modificarNoticia(id, titulo, cuerpo, imagenFileName);

            } catch (IOException ex) {
                
                System.out.println(ex.getMessage());
                
            }

        } else {

        noticiaService.modificarNoticia(id, titulo, cuerpo, imagenFileName);
        
        }

        return "redirect:../lista_noticias";

    }

    @GetMapping("/{id}")
    public String mostrarNoticia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaService.getOne(id));
        
        return "noticia.html";
        
    }
    
    
}
