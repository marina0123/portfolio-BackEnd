package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Persona;
import com.portfolio.portfolio.repository.IPersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired
    IPersonaRepository ipersonaRepository;
    
    public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
     
    public Optional<Persona> getOne(int id){
        return ipersonaRepository.findById(id);
    }
    
    public void save(Persona pers){
        ipersonaRepository.save(pers);
    }
     
    public void delete(int id){
        ipersonaRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return ipersonaRepository.existsById(id);
    }
}
