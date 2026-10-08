package tn.esprit.autoloc.domain;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) @ToString.Exclude @EqualsAndHashCode.Exclude
    Long idMaintenance;
    LocalDate dateDebut;
    LocalDate dateFin;
    String description;
}