package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;

import java.util.List;

public interface ApiServiceRegioner {
    List<Region> getRegioner();
    void pullRegioner();
    Region getRegionById(String kode);
    void deleteRegionById(String kode);
}
