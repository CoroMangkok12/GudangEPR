package com.epr.eprdemofe.models;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarangMasuk {
       
    private Integer no;
    private String code;
    private String nama;
    private Integer qty;
    private Date tanggal;

    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
