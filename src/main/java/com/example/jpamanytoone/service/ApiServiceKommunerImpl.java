package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceKommunerImpl implements ApiServiceKommuner {
    private final RestTemplate restTemplate;
    KommuneRepository kommuneRepository;

    public ApiServiceKommunerImpl(RestTemplate restTemplate, KommuneRepository kommuneRepository) {
        this.restTemplate = restTemplate;
        this.kommuneRepository = kommuneRepository;
    }

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(kommune -> kommuneRepository.save(kommune));
    }

    private Kommune findKommuneByKode(String kode){
        return kommuneRepository.findKommuneByKode(kode);
    }
    @Override
    public void pullKommuner(){
        String regionUrl = "https://api.dataforsyningen.dk/kommuner";

        ResponseEntity<List<Kommune>> listResponseEntity =
                restTemplate.exchange(regionUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Kommune>>(){});
        List<Kommune> kommuner = listResponseEntity.getBody();
        saveKommuner(kommuner);
    }

    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> kommuner = kommuneRepository.findAll();
        return kommuner;
    }

    @Override
    public void deleteKommune(String kode) {
        kommuneRepository.deleteById(kode);
    }

    @Override
    public Kommune getKommuneById(String kode){
        List<Kommune> kommuner = getKommuner();
        for (Kommune kommune : kommuner){
            if (kommune.getKode().equals(kode)){
                return kommune;
            }
        }
        return null;
    }
}
