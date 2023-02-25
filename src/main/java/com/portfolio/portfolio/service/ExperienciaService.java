package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Experiencia;
import com.portfolio.portfolio.repository.IExperienciaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {
    @Autowired
    IExperienciaRepository iexperienciaRepository;
     
    public List<Experiencia> list(){
        return iexperienciaRepository.findAll();
    }
     
    public Optional<Experiencia> getOne(int id){
        return iexperienciaRepository.findById(id);
    }
    
    public void save(Experiencia expe){
        iexperienciaRepository.save(expe);
    }
     
    public void delete(int id){
        iexperienciaRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return iexperienciaRepository.existsById(id);
    }
}
