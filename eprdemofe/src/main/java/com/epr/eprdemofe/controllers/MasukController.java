package com.epr.eprdemofe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MasukController {
    @GetMapping("/barangmasuk")
    public String getBarangMasukPage(Model model) {
        return "barangmasuk";
    }
}
