package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Habilidades;
import com.portfolio.portfolio.repository.IHabilidadesRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HabilidadesService {
    @Autowired
    IHabilidadesRepository ihabilidadesRepository;
     
    public List<Habilidades> list(){
        return ihabilidadesRepository.findAll();
    }
     
    public Optional<Habilidades> getOne(int id){
        return ihabilidadesRepository.findById(id);
    }
     
    public Optional<Habilidades> getByHabilidad(String habilidad){
        return ihabilidadesRepository.findByHabilidad(habilidad);
    }
     
    public void save(Habilidades hab){
        ihabilidadesRepository.save(hab);
    }
     
    public void delete(int id){
        ihabilidadesRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return ihabilidadesRepository.existsById(id);
    }
     
    public boolean existsByHabilidad(String habilidad){
        return ihabilidadesRepository.existsByHabilidad(habilidad);
    }
}
