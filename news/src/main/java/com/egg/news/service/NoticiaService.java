/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.service;

import com.egg.news.entity.Noticia;
import com.egg.news.exception.NewsException;
import com.egg.news.repositories.NoticiaRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Cruz
 */
@Service
public class NoticiaService {
    
    @Autowired
    private NoticiaRepository noticiaRepository;
    
    @Transactional
    public void crearNoticia(String titulo, String cuerpo, String imagen) throws NewsException{
        
        validarCreacionNoticia(titulo, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFechaNoticia(new Date());
        noticia.setImagen(imagen);
        
        noticiaRepository.save(noticia);
        
    }
    
    public List<Noticia> listarNoticias (){
        
        List<Noticia> listaNoticia = new ArrayList<>();
        
        listaNoticia = noticiaRepository.findAll();
        
        return listaNoticia;
    }
    
    public List<Noticia> listarTresUltimasNoticias(){
        
        List<Noticia> tresUltimasNoticias = noticiaRepository.listarNoticiasPorFecha();
        
        return tresUltimasNoticias;
        
    }
    
    
    @Transactional
    public void modificarNoticia ( String id, String titulo, String cuerpo, String imagen ) throws NewsException{
        
        validarCreacionNoticia(titulo, cuerpo);
        
        Optional<Noticia> respuesta = noticiaRepository.findById(id);
        if (respuesta.isPresent()) {
            
            Noticia noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setImagen(imagen);
            
            noticiaRepository.save(noticia);
            
        }
    }
    
    @Transactional
    public void eliminarNoticia ( String id ){
        
        noticiaRepository.deleteById(id);
    }
    
    public void validarCreacionNoticia(String titulo, String cuerpo) throws NewsException{
        
        if (titulo == null || titulo.isEmpty()) {
            
            throw new NewsException("El título de la noticia no puede estar vacío.");
            
        }
        
        if ( cuerpo == null || cuerpo.isEmpty() ){
            
            throw new NewsException("El cuerpo de la noticia no puede estar vacío.");
            
        }
        
    }
    
    public Noticia getOne(String id){
        
        return noticiaRepository.getOne(id);
        
    }
    
}
