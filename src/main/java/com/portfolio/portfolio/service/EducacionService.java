package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Educacion;
import com.portfolio.portfolio.repository.IEducacionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {
    @Autowired
    IEducacionRepository ieducacionRepository;
     
    public List<Educacion> list(){
        return ieducacionRepository.findAll();
    }
     
    public Optional<Educacion> getOne(int id){
        return ieducacionRepository.findById(id);
    }
     
    public Optional<Educacion> getByTitulo(String titulo){
        return ieducacionRepository.findByTitulo(titulo);
    }

    public void save(Educacion educ){
        ieducacionRepository.save(educ);
    }
     
    public void delete(int id){
        ieducacionRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return ieducacionRepository.existsById(id);
    }
     
    public boolean existsByTitulo(String titulo){
        return ieducacionRepository.existsByTitulo(titulo);
    }
}
