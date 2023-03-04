package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.DtoHabilidades;
import com.portfolio.portfolio.entity.Habilidades;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.HabilidadesService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habilidades")
@CrossOrigin(origins = "https://portfolio-web-argpro.web.app")
public class HabilidadesController {
    @Autowired
    HabilidadesService habilidadesService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Habilidades>> list(){
        List<Habilidades> list = habilidadesService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable("id") int id){
        if(!habilidadesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Habilidades habilidades = habilidadesService.getOne(id).get();
        return new ResponseEntity(habilidades, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!habilidadesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        habilidadesService.delete(id);
        return new ResponseEntity(new Mensaje("La habilidad ha sido eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHabilidades dtoHab){      
        if(StringUtils.isBlank(dtoHab.getHabilidad())) {
            return new ResponseEntity(new Mensaje("El nombre de la habilidad es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(habilidadesService.existsByHabilidad(dtoHab.getHabilidad())) {
            return new ResponseEntity(new Mensaje("El nombre de la habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }  
        
        Habilidades habilidades = new Habilidades(dtoHab.getHabilidad(), dtoHab.getPorcentaje());
        habilidadesService.save(habilidades);
        return new ResponseEntity(new Mensaje("La habilidad ha sido añadida"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHabilidades dtoHab){
        if(!habilidadesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if(habilidadesService.existsByHabilidad(dtoHab.getHabilidad()) && habilidadesService.getByHabilidad(dtoHab.getHabilidad()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El nombre de la habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoHab.getHabilidad())) {
            return new ResponseEntity(new Mensaje("El nombre de la habilidad es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Habilidades habilidades = habilidadesService.getOne(id).get();
        habilidades.setHabilidad(dtoHab.getHabilidad());
        habilidades.setPorcentaje(dtoHab.getPorcentaje());
        
        habilidadesService.save(habilidades);
        
        return new ResponseEntity(new Mensaje("La habilidad ha sido actualizada"), HttpStatus.OK);        
    }   
}
