package com.epr.eprdemofe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epr.eprdemofe.models.BarangMasuk;
import com.epr.eprdemofe.services.KirimService;

@Controller
@RequestMapping("/web")
public class KirimController {
    @Autowired
    private KirimService kirimService;

    @GetMapping("/kirim")
    public String getKirimPage(Model model) {
        List<BarangMasuk> barangMasukList = kirimService.getAllBarangMasuk();
        model.addAttribute("barangMasukList", barangMasukList);
        return "kirim";
    }
}
