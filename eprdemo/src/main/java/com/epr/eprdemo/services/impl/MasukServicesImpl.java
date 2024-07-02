package com.epr.eprdemo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epr.eprdemo.models.entity.Barangmasuk;
import com.epr.eprdemo.repositories.MasukRepository;
import com.epr.eprdemo.services.GenericService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MasukServicesImpl implements GenericService<Barangmasuk>{
    
    @Autowired
    private MasukRepository masukRepository;

    @Override
    public Barangmasuk save(Barangmasuk entity) {
        return masukRepository.save(entity);
    }

    @Override
    public Barangmasuk update(Barangmasuk entity) {
        return masukRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
      masukRepository.deleteById(id);
    }

    @Override
    public Barangmasuk findById(Integer id) {
        Optional<Barangmasuk> optional = masukRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Barangmasuk> findAll() {
        return masukRepository.findAll();
    }
}
