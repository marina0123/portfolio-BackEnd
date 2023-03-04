package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.dto.DtoBanner;
import com.portfolio.portfolio.entity.Banner;
import com.portfolio.portfolio.security.controller.Mensaje;
import com.portfolio.portfolio.service.BannerService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
@CrossOrigin(origins = "https://portfolio-web-argpro.web.app")
public class BannerController {
    @Autowired
    BannerService bannerService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Banner>> list(){
        List<Banner> list = bannerService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Banner> getById(@PathVariable("id") int id){
        if(!bannerService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        Banner banner = bannerService.getOne(id).get();
        return new ResponseEntity(banner, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoBanner dtoBann){
        if(!bannerService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        
        if(StringUtils.isBlank(dtoBann.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoBann.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoBann.getTitulo())) {
            return new ResponseEntity(new Mensaje("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoBann.getImg())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Banner banner = bannerService.getOne(id).get();
        banner.setNombre(dtoBann.getNombre());
        banner.setApellido(dtoBann.getApellido());   
        banner.setTitulo(dtoBann.getTitulo());
        banner.setImg(dtoBann.getImg());
        
        bannerService.save(banner);
        
        return new ResponseEntity(new Mensaje("El banner ha sido actualizado"), HttpStatus.OK);        
    }   
}
