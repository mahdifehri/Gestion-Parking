package com.example.fehrimahdi.repository;

import com.example.fehrimahdi.entity.Parking;
import com.example.fehrimahdi.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking,Integer> {
    Parking findFirstByAdresseIs(String adresse);
}
