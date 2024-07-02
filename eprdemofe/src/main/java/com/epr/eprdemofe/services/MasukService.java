package com.epr.eprdemofe.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.epr.eprdemofe.models.BarangMasuk;

@Service
public class MasukService {
    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9005/barangmasuk";

    public List<BarangMasuk> getAllBarangMasuk() {
        BarangMasuk[] barangMasukArray = restTemplate.getForObject(BASE_URL, BarangMasuk[].class);
        return barangMasukArray != null ? Arrays.asList(barangMasukArray) : Collections.emptyList();
    }

    public BarangMasuk getBarangMasukById(Integer id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, BarangMasuk.class);
    }

    public BarangMasuk createBarangMasuk(BarangMasuk barangMasuk) {
        return restTemplate.postForObject(BASE_URL, barangMasuk, BarangMasuk.class);
    }

    public void updateBarangMasuk(BarangMasuk barangMasuk) {
        restTemplate.put(BASE_URL, barangMasuk);
    }

    public void deleteBarangMasukById(Integer id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}