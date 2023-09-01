package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.service.ApiServiceRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {
    @Autowired
    ApiServiceRegioner apiServiceRegioner;
    @GetMapping("/regioner")
    public List<Region> getRegioner(){
        return apiServiceRegioner.getRegioner();
    }

    @GetMapping("/region/{name}")
    public List<Region> getRegionByName(@PathVariable String navn){
        return null;
    }
}
