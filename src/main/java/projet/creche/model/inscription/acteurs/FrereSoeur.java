package projet.creche.model.inscription.acteurs;

import jakarta.persistence.*;

import java.sql.Date;

/**
 * Entité représentant un frère ou une sœur.
 */
@Entity
@Table(name = "frere_soeur")
public class FrereSoeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom", nullable = false, columnDefinition = "VARCHAR(30)")
    private String firstName;

    @Column(name = "nom", nullable = false, columnDefinition = "VARCHAR(30)")
    private String lastName;

    @Column(name = "date_naissance", nullable = true)
    private Date dateNaissance;

    // Constructeur par défaut
    public FrereSoeur() {}

    // Constructeur avec paramètres
    public FrereSoeur(String firstName, String lastName, Date dateNaissance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "FrereSoeur{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
