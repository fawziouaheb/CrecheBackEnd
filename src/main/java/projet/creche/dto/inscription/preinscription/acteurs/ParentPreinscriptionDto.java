package projet.creche.dto.inscription.preinscription.acteurs;

import java.sql.Date;

public class ParentPreinscriptionDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private String adresse;
    private String telephoneDomicile;
    private String telephonePortable;
    private String email;
    private String profession;
    public ParentPreinscriptionDto() {}

    // Constructeur avec paramètres
    public ParentPreinscriptionDto(String firstName, String lastName, Date dateNaissance, String adresse,
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
        return "ParentDto{" +
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
