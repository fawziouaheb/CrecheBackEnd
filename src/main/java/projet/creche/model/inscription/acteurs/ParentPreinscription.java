package projet.creche.model.inscription.acteurs;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "parent_preinscription")
public class ParentPreinscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom", nullable = false, columnDefinition = "VARCHAR(30)")
    private String firstName;

    @Column(name = "nom", nullable = false, columnDefinition = "VARCHAR(30)")
    private String lastName;

    @Column(name = "date_naissance", nullable = false)
    private Date dateNaissance;

    @Column(name = "adresse", nullable = false, columnDefinition = "VARCHAR(255)")
    private String adresse;

    @Column(name = "telephone_domicile", nullable = false, columnDefinition = "VARCHAR(15)")
    private String telephoneDomicile;

    @Column(name = "telephone_portable", nullable = false, columnDefinition = "VARCHAR(15)")
    private String telephonePortable;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "profession", nullable = false, columnDefinition = "VARCHAR(50)")
    private String profession;

    // Constructeur par défaut
    public ParentPreinscription() {}

    // Constructeur avec paramètres
    public ParentPreinscription(String firstName, String lastName, Date dateNaissance, String adresse,
                  String telephoneDomicile, String telephonePortable, String email, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephoneDomicile = telephoneDomicile;
        this.telephonePortable = telephonePortable;
        this.email = email;
        this.profession = profession;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephoneDomicile() {
        return telephoneDomicile;
    }

    public void setTelephoneDomicile(String telephoneDomicile) {
        this.telephoneDomicile = telephoneDomicile;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse='" + adresse + '\'' +
                ", telephoneDomicile='" + telephoneDomicile + '\'' +
                ", telephonePortable='" + telephonePortable + '\'' +
                ", email='" + email + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
