package tn.esprit.autoloc.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;

@Component
@Profile("dev")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInitializer implements CommandLineRunner {

    VehiculeRepository vehiculeRepository;

    @Override
    public void run(String... args) {
        if (vehiculeRepository.count() > 0) {
            return; // évite les doublons
        }

        vehiculeRepository.save(creer("123 TUN 4567", "Renault", "Clio",
                CategorieVehicule.CITADINE, new BigDecimal("80.00"), StatutVehicule.DISPONIBLE));
        vehiculeRepository.save(creer("234 TUN 8910", "Peugeot", "508",
                CategorieVehicule.BERLINE, new BigDecimal("150.00"), StatutVehicule.LOUE));
        vehiculeRepository.save(creer("345 TUN 1112", "Kia", "Sportage",
                CategorieVehicule.SUV, new BigDecimal("180.00"), StatutVehicule.MAINTENANCE));

        System.out.println(">>> Démo : " + vehiculeRepository.count() + " véhicules insérés");
    }

    Vehicule creer(String immatriculation, String marque, String modele,
                   CategorieVehicule categorie, BigDecimal tarif, StatutVehicule statut) {
        Vehicule v = new Vehicule();
        v.setImmatriculation(immatriculation);
        v.setMarque(marque);
        v.setModele(modele);
        v.setCategorie(categorie);
        v.setTarifJournalier(tarif);
        v.setStatut(statut);
        return v;
    }
}