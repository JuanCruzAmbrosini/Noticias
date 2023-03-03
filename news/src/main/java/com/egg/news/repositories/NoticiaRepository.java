/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.repositories;

import com.egg.news.entity.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Cruz
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, String>{
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public String buscarNoticiaPorTitulo (@Param ("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n ORDER BY n.fechaNoticia DESC")
    public List<Noticia> listarNoticiasPorFecha();
            
}
