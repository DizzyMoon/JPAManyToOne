package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceRegionerImpl implements ApiServiceRegioner {
    private final RestTemplate restTemplate;
    RegionRepository regionRepository;

    public ApiServiceRegionerImpl(RestTemplate restTemplate, RegionRepository regionRepository) {
        this.restTemplate = restTemplate;
        this.regionRepository = regionRepository;
    }

    private void saveRegions(List<Region> regions) {
        /*
        for (Region region : regions) {
            regionRepository.save(region);
        }
         */

        regions.forEach(region -> regionRepository.save(region));
    }

    @Override
    public void pullRegioner() {
        String regionUrl = "https://api.dataforsyningen.dk/regioner";


        ResponseEntity<List<Region>> listResponseEntity =
                restTemplate.exchange(regionUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Region>>() {
                        });
        List<Region> regions = listResponseEntity.getBody();
        saveRegions(regions);
    }

    @Override
    public void deleteRegionById(String kode){
        regionRepository.delete(getRegionById(kode));
    }

    @Override
    public List<Region> getRegioner() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(String kode){
        List<Region> regioner = getRegioner();
        for (Region region : regioner) {
            if (region.getKode().equals(kode)){
                return region;
            }
        }
        return null;
    }
}
