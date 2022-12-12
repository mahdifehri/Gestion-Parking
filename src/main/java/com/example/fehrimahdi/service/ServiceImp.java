package com.example.fehrimahdi.service;

import com.example.fehrimahdi.entity.Parking;
import com.example.fehrimahdi.entity.Personnel;
import com.example.fehrimahdi.entity.Poste;
import com.example.fehrimahdi.entity.Zone;
import com.example.fehrimahdi.repository.ParkingRepository;
import com.example.fehrimahdi.repository.PersonnelRepository;
import com.example.fehrimahdi.repository.ZoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceImp {
    ParkingRepository parkingRepository;
    PersonnelRepository personnelRepository;
    ZoneRepository zoneRepository;

    public Personnel ajouterPersonnel(Personnel personnel){
        return personnelRepository.save(personnel);
    }
    public void ajouterPerkingetZones(Parking parking){
        List<Zone> zones=parking.getZones();
        for(Zone zone:zones){
            zoneRepository.save(zone);
        }
        parkingRepository.save(parking);
    }
    public void affecterPersonnelZone(int idZone,int idGarde){
        Zone zone=zoneRepository.findById(idZone).orElse(null);
        Personnel garde=personnelRepository.findById(idGarde).orElse(null);
            if(zone!=null && garde!=null){
              zone.getGardes().add(garde);
              zoneRepository.save(zone);
            }
    }

    public List<Personnel> getAllPersonnelByParking(Parking parking){
        List<Personnel> personnels = new ArrayList<Personnel>();
        List<Zone>zones=zoneRepository.findByParking_Id(parking.getId());
                for(Zone zone:zones){
                    List<Personnel> personnels1=zone.getGardes();
                         for (Personnel personnel1:personnels1){
                           personnels.add(personnel1);
                         }

                    personnels.add(zone.getResponsable());
                }
        return personnels;
    }

    public Integer nombreGardeJour(String adresse){
        int nbr=0;
        Parking parking=parkingRepository.findFirstByAdresseIs(adresse);
        List<Personnel> personnels=getAllPersonnelByParking(parking);
        System.out.println(personnels);
        if(personnels!=null) {
            for (Personnel personnel : personnels) {
                if(personnel!=null) {
                    if (personnel.getPoste() == Poste.GARDE_JOUR) {
                        nbr += 1;
                    }
                }
            }
        }else {System.out.println("error");}
        return nbr;
    }
    public List<Personnel> getPersonnelByDate(Date startDate,Date endDate){
        List<Personnel> personnels = personnelRepository.findByDateDeRecrutementAfterAndDateDeRecrutementBefore(startDate,endDate);
        return personnels;

    }
    @Scheduled(cron = "* 30 * * * ?")
    public void getNbrGardesByZone(){

    }
}
