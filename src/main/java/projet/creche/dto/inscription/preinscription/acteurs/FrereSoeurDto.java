package projet.creche.dto.inscription.preinscription.acteurs;

import java.sql.Date;

public class FrereSoeurDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    public FrereSoeurDto() {}

    // Constructeur avec paramètres
    public FrereSoeurDto(String firstName, String lastName, Date dateNaissance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
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
