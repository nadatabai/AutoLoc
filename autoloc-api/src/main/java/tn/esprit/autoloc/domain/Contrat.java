package tn.esprit.autoloc.domain;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @ToString.Exclude @EqualsAndHashCode.Exclude
    Long idContrat;
    LocalDate dateSignature;
    Double montantTotal;
    Boolean valide;
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    List<Paiement> paiements = new ArrayList<>();
    public void addPaiement(Paiement p) {
        paiements.add(p);
        p.setContrat(this);
    }
    public void removePaiement(Paiement p) {
        paiements.remove(p);
        p.setContrat(null);
    }
}