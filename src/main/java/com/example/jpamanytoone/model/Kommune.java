package com.example.jpamanytoone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Kommune {
    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    @JsonBackReference
    private Region region;

    public String getKode() {
        return kode;
    }

    public String getHref() {
        return href;
    }

    public String getNavn() {
        return navn;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


}
