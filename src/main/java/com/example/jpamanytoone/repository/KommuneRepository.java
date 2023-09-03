package com.example.jpamanytoone.repository;

import com.example.jpamanytoone.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KommuneRepository extends JpaRepository<Kommune, String> {
    public Kommune findKommuneByKode(String kode);
}
