package com.epr.eprdemofe.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epr.eprdemofe.models.BarangMasuk;
import com.epr.eprdemofe.services.MasukService;


@RestController
@RequestMapping("/api/barangmasuk")
public class RestMasukController {
    @Autowired
    private MasukService masukService;

    @GetMapping
    public List<BarangMasuk> getAllBarangMasuk() {
        return masukService.getAllBarangMasuk();
    }

    @GetMapping("/{id}")
    public BarangMasuk getBarangMasukById(@PathVariable Integer id) {
        return masukService.getBarangMasukById(id);
    }

    @PostMapping
    public BarangMasuk createBarangMasuk(@RequestBody BarangMasuk barangMasuk) {
        return masukService.createBarangMasuk(barangMasuk);
    }

    @PutMapping
    public void updateBarangMasuk(@RequestBody BarangMasuk barangMasuk) {
        masukService.updateBarangMasuk(barangMasuk);
    }

    @DeleteMapping("/{id}")
    public void deleteBarangMasukById(@PathVariable Integer id) {
        masukService.deleteBarangMasukById(id);
    }
}
