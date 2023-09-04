package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.service.ApiServiceKommuner;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class KommuneRestController {
    @Autowired
    ApiServiceKommuner apiServiceKommuner;

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner() {
        return apiServiceKommuner.getKommuner();
    }

    @GetMapping("/kommune/{kode}")
    public Kommune getKommuneById(@PathVariable String kode) {
        return apiServiceKommuner.getKommuneById(kode);
    }

    @DeleteMapping("/kommune/{kode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteKommune(@PathVariable String kode){
        Optional<Kommune> optionalKommune = Optional.ofNullable(apiServiceKommuner.getKommuneById(kode));
        if(optionalKommune.isPresent()){
            apiServiceKommuner.deleteKommune(kode);
            return ResponseEntity.ok("Kommune deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommune not found");
        }
    }

    @PutMapping("/kommune/{kode}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Kommune> updateKommune(@PathVariable("kode") String kode, @RequestBody Kommune kommune){
        Optional<Kommune> optionalKommune = Optional.ofNullable(apiServiceKommuner.getKommuneById(kode));
        if (optionalKommune.isPresent()){
            kommune.setKode(kode);
            apiServiceKommuner.saveKommune(kommune);
            return ResponseEntity.ok(kommune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/kommune")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune postKommune(@RequestBody Kommune kommune){
        System.out.println(kommune);
        return apiServiceKommuner.saveKommune(kommune);
    }
}
