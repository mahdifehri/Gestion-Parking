package com.example.fehrimahdi.controller;

import com.example.fehrimahdi.entity.Parking;
import com.example.fehrimahdi.entity.Personnel;
import com.example.fehrimahdi.service.ServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("exam")
@AllArgsConstructor
public class controller {
    ServiceImp service;
    @PostMapping("/ajouterPersonnel")
    public Personnel ajouterPersonnel(@RequestBody Personnel personnel){
        return service.ajouterPersonnel(personnel);
    }
    @PostMapping("/ajouterPerkingetZones")
    public void ajouterPerkingetZones(@RequestBody Parking parking){
        service.ajouterPerkingetZones(parking);
    }
    @PutMapping("affecterPersonnelZone/{idZone}/{idGarde}")
    public void affecterPersonnelZone(@PathVariable int idZone,@PathVariable int idGarde){
        service.affecterPersonnelZone(idZone,idGarde);
    }
    @PutMapping("/getAllPersonnelByParking")
    public List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking){
        return service.getAllPersonnelByParking(parking);
    }
    @GetMapping("/nombreGardeJour/{adresse}")
    public Integer nombreGardeJour(@PathVariable String adresse){
        return service.nombreGardeJour(adresse);
    }
    @GetMapping("/getPersonnelByDate/{startDate}/{endDate}")
    public List<Personnel> getPersonnelByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                              @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate){
        return service.getPersonnelByDate(startDate,endDate);
    }
}
