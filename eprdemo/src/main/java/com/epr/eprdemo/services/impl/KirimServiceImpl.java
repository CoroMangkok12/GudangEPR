package com.epr.eprdemo.services.impl;

import com.epr.eprdemo.models.entity.Kirim;
import com.epr.eprdemo.repositories.KirimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KirimServiceImpl {
    @Autowired
    private KirimRepository kirimRepository;

    public List<Kirim> findAll() {
        return kirimRepository.findAll();
    }

    public Optional<Kirim> findById(String code) {
        return kirimRepository.findById(code);
    }

    public Kirim save(Kirim kirim) {
        return kirimRepository.save(kirim);
    }

    public void deleteById(String code) {
        kirimRepository.deleteById(code);
    }
}
