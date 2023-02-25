package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBannerRepository extends JpaRepository<Banner,Integer> {}
