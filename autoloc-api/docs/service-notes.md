## D. Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| `@Autowired` sur un champ de `ContratServiceImpl` | S6813 : injection par attribut déconseillée | Injection par constructeur avec `@RequiredArgsConstructor` et champ `final` |
| `RuntimeException` levée dans `findById` | S112 : exception générique | Remplacée par `ResourceNotFoundException("Contrat", id)` |
| Import inutilisé `java.math.BigDecimal` dans `ContratServiceImpl` | Import inutile, si confirmé par IntelliJ | Import supprimé |