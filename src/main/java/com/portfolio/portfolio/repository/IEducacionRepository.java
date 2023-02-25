package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
}
