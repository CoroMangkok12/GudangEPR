package com.epr.eprdemo.models.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_barangmasuk")
public class Barangmasuk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_barang")
    private Integer no;

    @Column(name = "code_barang", nullable = false, length = 20, unique = true)
    private String code;

    @Column(name = "nama_barang", nullable = false, length = 50, unique = true)
    private String nama;

    @Column(name = "qty_barang", nullable = false)
    private Integer qty;

    @Column(name = "tanggal", nullable = false, length = 20)
    private Date tanggal;

    @OneToMany(mappedBy = "barangmasuk", cascade = CascadeType.ALL)
    private List<Kirim> kirimList;

}
