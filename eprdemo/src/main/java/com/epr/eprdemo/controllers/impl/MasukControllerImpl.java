package com.epr.eprdemo.controllers.impl;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epr.eprdemo.controllers.GenericController;
import com.epr.eprdemo.models.entity.Barangmasuk;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/barangmasuk")
public class MasukControllerImpl extends GenericController<Barangmasuk> {
    
}
