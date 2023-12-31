package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RegionRestController {
    @Autowired
    ApiServiceRegioner apiServiceRegioner;
    @GetMapping("/regioner")
    public List<Region> getAllRegions(){
        return apiServiceRegioner.getRegioner();
    }

    @GetMapping("/region/{kode}")
    public Region getRegionByName(@PathVariable String kode){
        return apiServiceRegioner.getRegionById(kode);
    }

    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return apiServiceRegioner.saveRegion(region);
    }

    @DeleteMapping("region/{kode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRegion(@PathVariable String kode){
        Optional<Region> optionalRegion = Optional.ofNullable(apiServiceRegioner.getRegionById(kode));
        if (optionalRegion.isPresent()){
            Region region = optionalRegion.get();
            if (!(region.getKommuneSet().isEmpty())){
                return ResponseEntity.badRequest().body("Cannot delete region with associated kommune's");
            }
            apiServiceRegioner.deleteRegionById(kode);
            return ResponseEntity.ok("Region deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Region not found");
        }
    }

    @PutMapping("/region/{kode}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Region> updateRegion(@PathVariable("kode") String kode, @RequestBody Region region){
        Optional<Region> optionalRegion = Optional.ofNullable(apiServiceRegioner.getRegionById(kode));
        if (optionalRegion.isPresent()){
            region.setKode(kode);
            apiServiceRegioner.saveRegion(region);
            return ResponseEntity.ok(region);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/kommunenavne/{kode}")
    public List<String> getKommuneNavneByRegion(@PathVariable("kode") String kode){
        return apiServiceRegioner.getRegionById(kode).getKommuneNavne();
    }
}
