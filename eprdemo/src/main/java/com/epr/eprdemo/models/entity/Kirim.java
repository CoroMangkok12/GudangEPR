package com.epr.eprdemo.models.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_kirim")
public class Kirim {
    @Id
    @Column(name = "code_kirim", nullable = false, length = 20, unique = true)
    private String code;

    @Column(name = "nama_kirim", nullable = false, length = 50, unique = true)
    private String nama;

    @Column(name = "qty_kirim", nullable = false)
    private Integer qty;

    @Column(name = "tanggal", nullable = false, length = 20)
    private Date tanggal;

    @ManyToOne
    @JoinColumn(name = "no_barang", nullable = false)
    private Barangmasuk barangmasuk;
}
