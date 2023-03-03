/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controller;

import com.egg.news.entity.Noticia;
import java.util.List;
import com.egg.news.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Juan Cruz
 */
@Controller
@RequestMapping("/")

public class HomeController {
    
    @Autowired
    NoticiaService noticiaService = new NoticiaService();
    
    @GetMapping("/")
    
    public String index( ModelMap modelo ){
        
        List<Noticia> noticias = noticiaService.listarTresUltimasNoticias();

        if (!noticias.isEmpty()) {

            modelo.addAttribute("noticias", noticias);

        } else {

            modelo.put("vacio", "¡Aún no hay noticias para mostrar!");

        }
        
        return "index.html";    
    }
    
}
