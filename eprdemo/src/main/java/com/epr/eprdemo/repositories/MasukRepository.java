package com.epr.eprdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.epr.eprdemo.models.entity.Barangmasuk;

@Repository
public interface MasukRepository extends JpaRepository<Barangmasuk, Integer>{
    
}
