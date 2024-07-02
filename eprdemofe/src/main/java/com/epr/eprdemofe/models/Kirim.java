package com.epr.eprdemofe.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kirim {
       
    private String code;
    private String nama;
    private Integer qty;
    private Date tanggal;
}
