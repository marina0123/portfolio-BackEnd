package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.DtoEducacion;
import com.portfolio.portfolio.entity.Educacion;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://portfolio-web-argpro.web.app")
public class EducacionController {
    @Autowired
    EducacionService educacionService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("La educación ha sido eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEduc){      
        if(StringUtils.isBlank(dtoEduc.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre del título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(educacionService.existsByTitulo(dtoEduc.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre del título ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoEduc.getInstituto())) {
            return new ResponseEntity(new Mensaje("El nombre del instituto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
       
        if(StringUtils.isBlank(dtoEduc.getPeriodo())) {
            return new ResponseEntity(new Mensaje("El periodo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(dtoEduc.getTitulo(), dtoEduc.getInstituto(), dtoEduc.getPeriodo());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("La educación ha sido añadida"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEduc){
        if(!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if(educacionService.existsByTitulo(dtoEduc.getTitulo()) && educacionService.getByTitulo(dtoEduc.getTitulo()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El nombre del título ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoEduc.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre del título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoEduc.getInstituto())) {
            return new ResponseEntity(new Mensaje("El nombre del instituto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoEduc.getPeriodo())) {
            return new ResponseEntity(new Mensaje("El periodo es obligatorio"), HttpStatus.BAD_REQUEST);
        }       
        
        Educacion educacion = educacionService.getOne(id).get();
        educacion.setTitulo(dtoEduc.getTitulo());
        educacion.setInstituto(dtoEduc.getInstituto());
        educacion.setPeriodo(dtoEduc.getPeriodo());
        
        educacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje("La educación ha sido actualizada"), HttpStatus.OK);        
    }
}
