package com.epr.eprdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epr.eprdemo.models.entity.Kirim;

public interface KirimRepository extends JpaRepository<Kirim, String> {
    
}
