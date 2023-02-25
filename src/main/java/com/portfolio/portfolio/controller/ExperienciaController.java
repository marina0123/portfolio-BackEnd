package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.DtoExperiencia;
import com.portfolio.portfolio.entity.Experiencia;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.ExperienciaService;
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
@RequestMapping("/experiencia")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = experienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Experiencia experiencia = experienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        experienciaService.delete(id);
        return new ResponseEntity(new Mensaje("La experiencia ha sido eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExpe){      
        if(StringUtils.isBlank(dtoExpe.getPuesto())) {
            return new ResponseEntity(new Mensaje("El nombre del puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getEmpresa())) {
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción de las tareas realizadas es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getPeriodo())) {
            return new ResponseEntity(new Mensaje("El periodo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia = new Experiencia(dtoExpe.getPuesto(), dtoExpe.getEmpresa(), dtoExpe.getDescripcion(), dtoExpe.getPeriodo());
        experienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("La experiencia ha sido añadida"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExpe){
        if(!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        if(StringUtils.isBlank(dtoExpe.getPuesto())) {
            return new ResponseEntity(new Mensaje("El nombre del puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getEmpresa())) {
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción de las tareas realizadas es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoExpe.getPeriodo())) {
            return new ResponseEntity(new Mensaje("El periodo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
               
        Experiencia experiencia = experienciaService.getOne(id).get();
        experiencia.setPuesto(dtoExpe.getPuesto());
        experiencia.setEmpresa(dtoExpe.getEmpresa());
        experiencia.setDescripcion(dtoExpe.getDescripcion());
        experiencia.setPeriodo(dtoExpe.getPeriodo());
                
        experienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("La experiencia ha sido actualizada"), HttpStatus.OK);        
    }
}
