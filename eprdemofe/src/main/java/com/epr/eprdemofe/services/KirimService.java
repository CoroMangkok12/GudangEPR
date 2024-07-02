package com.epr.eprdemofe.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epr.eprdemofe.models.BarangMasuk;

@Service
public class KirimService {
    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9005/barangmasuk";

    public List<BarangMasuk> getAllBarangMasuk() {
        BarangMasuk[] barangMasukArray = restTemplate.getForObject(BASE_URL, BarangMasuk[].class);
        return Arrays.asList(barangMasukArray);
    }
}
