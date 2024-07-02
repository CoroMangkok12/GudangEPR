package com.epr.eprdemofe.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epr.eprdemofe.models.BarangMasuk;
import com.epr.eprdemofe.services.KirimService;

@RestController
@RequestMapping("/api/kirim")
public class RestKirimController {
    @Autowired
    private KirimService kirimService;

    @GetMapping
    public List<BarangMasuk> getAllBarangMasuk() {
        return kirimService.getAllBarangMasuk();
    }
}
