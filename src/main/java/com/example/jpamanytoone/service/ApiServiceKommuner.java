package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;

import java.util.List;

public interface ApiServiceKommuner {
    List<Kommune> getKommuner();
    void deleteKommune(String kode);
    Kommune getKommuneById(String kode);
    void pullKommuner();
}
