package com.example.fehrimahdi.repository;

import com.example.fehrimahdi.entity.Personnel;
import com.example.fehrimahdi.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
    public List<Personnel> findByDateDeRecrutementAfterAndDateDeRecrutementBefore(Date startDate,Date endDate);
}
