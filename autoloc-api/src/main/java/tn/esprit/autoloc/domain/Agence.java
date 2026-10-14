package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @ToString.Exclude @EqualsAndHashCode.Exclude
    Long idAgence;
    String nom;
    String ville;
    String adresse;
    String telephone;
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    List<Vehicule> vehicules = new ArrayList<>();
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    List<Employe> employes = new ArrayList<>();
}