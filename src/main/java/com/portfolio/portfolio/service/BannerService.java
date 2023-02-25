package com.portfolio.portfolio.service;

import com.portfolio.portfolio.entity.Banner;
import com.portfolio.portfolio.repository.IBannerRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BannerService {
    @Autowired
    IBannerRepository ibannerRepository;
    
    public List<Banner> list(){
        return ibannerRepository.findAll();
    }
     
    public Optional<Banner> getOne(int id){
        return ibannerRepository.findById(id);
    }
     
    public void save(Banner bann){
        ibannerRepository.save(bann);
    }
     
    public void delete(int id){
        ibannerRepository.deleteById(id);
    }
     
    public boolean existsById(int id){
        return ibannerRepository.existsById(id);
    }
}
