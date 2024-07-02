package com.epr.eprdemo.controllers.impl;

import com.epr.eprdemo.models.entity.Kirim;
import com.epr.eprdemo.services.impl.KirimServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kirim")
public class KirimControllerImpl {

    @Autowired
    private KirimServiceImpl kirimServiceImpl;

    @GetMapping
    public List<Kirim> findAll() {
        return kirimServiceImpl.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Kirim> findById(@PathVariable String code) {
        Optional<Kirim> kirim = kirimServiceImpl.findById(code);
        return kirim.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kirim save(@RequestBody Kirim kirim) {
        return kirimServiceImpl.save(kirim);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteById(@PathVariable String code) {
        kirimServiceImpl.deleteById(code);
        return ResponseEntity.ok().build();
    }
}
