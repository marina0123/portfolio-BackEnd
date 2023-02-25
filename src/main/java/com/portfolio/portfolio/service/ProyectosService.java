package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Proyectos;
import com.portfolio.portfolio.repository.IProyectosRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectosService {
    @Autowired
    IProyectosRepository iproyectosRepository;
     
    public List<Proyectos> list(){
        return iproyectosRepository.findAll();
    }
     
    public Optional<Proyectos> getOne(int id){
        return iproyectosRepository.findById(id);
    }
     
    public Optional<Proyectos> getByNombre(String nombre){
        return iproyectosRepository.findByNombre(nombre);
    }
    
    public Optional<Proyectos> getByLink(String link){
        return iproyectosRepository.findByLink(link);
    }
    
    public void save(Proyectos proy){
        iproyectosRepository.save(proy);
    }
     
    public void delete(int id){
        iproyectosRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return iproyectosRepository.existsById(id);
    }
     
    public boolean existsByNombre(String nombre){
        return iproyectosRepository.existsByNombre(nombre);
    }
    
    public boolean existsByLink(String link){
        return iproyectosRepository.existsByLink(link);
    }     
}
