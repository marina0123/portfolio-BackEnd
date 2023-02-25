package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.DtoProyectos;
import com.portfolio.portfolio.entity.Proyectos;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.ProyectosService;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectosController {
    @Autowired
    ProyectosService proyectosService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = proyectosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!proyectosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Proyectos proyectos = proyectosService.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        proyectosService.delete(id);
        return new ResponseEntity(new Mensaje("El proyecto ha sido eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoProy){      
        if(StringUtils.isBlank(dtoProy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(proyectosService.existsByNombre(dtoProy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
               
        if(StringUtils.isBlank(dtoProy.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoProy.getFecha())) {
            return new ResponseEntity(new Mensaje("La fecha de realización del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
                
        if(StringUtils.isBlank(dtoProy.getLink())) {
            return new ResponseEntity(new Mensaje("La URL del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(proyectosService.existsByLink(dtoProy.getLink())) {
            return new ResponseEntity(new Mensaje("La URL del proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = new Proyectos(dtoProy.getNombre(), dtoProy.getDescripcion(), dtoProy.getFecha(), dtoProy.getLink());
        proyectosService.save(proyectos);
        return new ResponseEntity(new Mensaje("El proyecto ha sido añadido"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoProy){
        if(!proyectosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if(proyectosService.existsByNombre(dtoProy.getNombre()) && proyectosService.getByNombre(dtoProy.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProy.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoProy.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoProy.getFecha())) {
            return new ResponseEntity(new Mensaje("La fecha de realización del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(proyectosService.existsByLink(dtoProy.getLink()) && proyectosService.getByLink(dtoProy.getLink()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La URL del proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProy.getLink())) {
            return new ResponseEntity(new Mensaje("La URL del proyecto es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = proyectosService.getOne(id).get();
        proyectos.setNombre(dtoProy.getNombre());
        proyectos.setDescripcion(dtoProy.getDescripcion());
        proyectos.setFecha(dtoProy.getFecha());
        proyectos.setLink(dtoProy.getLink());
        
        proyectosService.save(proyectos);
        
        return new ResponseEntity(new Mensaje("El proyecto ha sido actualizado"), HttpStatus.OK);        
    }   
}
