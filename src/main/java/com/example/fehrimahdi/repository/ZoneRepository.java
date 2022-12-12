package com.example.fehrimahdi.repository;

import com.example.fehrimahdi.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone,Integer> {
    public List<Zone> findByParking_Id(int id);
}
