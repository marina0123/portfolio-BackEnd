package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.Habilidades;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabilidadesRepository extends JpaRepository<Habilidades, Integer> {
    public Optional<Habilidades> findByHabilidad(String habilidad);
    public boolean existsByHabilidad(String habilidad);
}
