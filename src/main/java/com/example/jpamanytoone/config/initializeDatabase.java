package com.example.jpamanytoone.config;

import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.repository.RegionRepository;
import com.example.jpamanytoone.service.ApiServiceKommuner;
import com.example.jpamanytoone.service.ApiServiceRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initializeDatabase implements CommandLineRunner {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    KommuneRepository kommuneRepository;
    @Autowired
    ApiServiceKommuner apiServiceKommuner;
    @Autowired
    ApiServiceRegioner apiServiceRegioner;
    @Override
    public void run(String... args) throws  Exception {
        apiServiceRegioner.pullRegioner();
        apiServiceKommuner.pullKommuner();
    }
}
