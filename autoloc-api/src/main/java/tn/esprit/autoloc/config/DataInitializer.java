package tn.esprit.autoloc.config;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
@Component
@Profile("dev")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInitializer implements CommandLineRunner {
    IVehiculeRepository vehiculeRepository;
    IContratRepository contratRepository;
    @Override
    @Transactional
    public void run(String... args) {
        initVehicules();
        initContrats();
    }
    void initVehicules() {
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
    void initContrats() {
        if (contratRepository.count() > 0) {
            return;
        }
        Contrat c = new Contrat();
        c.setDateSignature(LocalDate.now());
        c.setMontantTotal(500.0);
        c.setValide(true);

        c.addPaiement(creerPaiement("200.00"));
        c.addPaiement(creerPaiement("300.00"));
        contratRepository.save(c);

        System.out.println(">>> Démo : contrat " + c.getIdContrat()
                + " avec " + c.getPaiements().size() + " paiements");
        c.removePaiement(c.getPaiements().get(0));
        Contrat relu = contratRepository.findById(c.getIdContrat()).orElseThrow();
        System.out.println(">>> Contrat relu : " + relu.getDateSignature());
        System.out.println(">>> Nombre de paiements : " + relu.getPaiements().size());
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
    Paiement creerPaiement(String montant) {
        Paiement p = new Paiement();
        p.setMontant(new BigDecimal(montant));
        p.setDatePaiement(LocalDate.now());
        p.setModePaiement(ModePaiement.values()[0]);
        return p;
    }
}