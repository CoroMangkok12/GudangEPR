package com.epr.eprdemofe.controllers;

import java.util.Objects;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epr.eprdemofe.models.BarangMasuk;
import com.epr.eprdemofe.services.MasukService;

@Controller
@RequestMapping("/web")
public class DashboardController {

    @Autowired
    private MasukService masukService;

    @GetMapping("/dashboard")
    public String getDashboardPage(Model model) {
        List<BarangMasuk> allItems = masukService.getAllBarangMasuk();

        if (allItems != null && !allItems.isEmpty()) {
            long totalUniqueItems = allItems.stream()
                                            .map(BarangMasuk::getCode)
                                            .filter(Objects::nonNull)
                                            .distinct()
                                            .count();

            int totalQuantity = allItems.stream()
                                        .mapToInt(item -> item.getQty() != null ? item.getQty() : 0)
                                        .sum();

            LocalDateTime lastActivity = allItems.stream()
                                                .map(BarangMasuk::getTimestamp)
                                                .filter(Objects::nonNull)
                                                .max(LocalDateTime::compareTo)
                                                .orElse(LocalDateTime.now());

            model.addAttribute("totalUniqueItems", totalUniqueItems);
            model.addAttribute("totalQuantity", totalQuantity);
            model.addAttribute("lastActivity", lastActivity);
        } else {
            model.addAttribute("totalUniqueItems", 0L);
            model.addAttribute("totalQuantity", 0);
            model.addAttribute("lastActivity", LocalDateTime.now());
        }

        return "dashboard";
        }
}
