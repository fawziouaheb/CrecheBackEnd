package projet.creche.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ce model contient les informations supplémentaires pour les employé
 * @author Fawzi Ouaheb
 */
@Entity( name = "Employé")
@Table ( name = "employe")
public class Employe extends  Person {

    // employe type_de_contrat, date_debut_contrat, date_fin_conctrat,
    @Column(name = "type_contract", nullable = false, columnDefinition = "VARCHAR(20)")
    private String typeContract;

    @Column(name = "date_begin_contract", nullable = false)
    private Date dateBeginContract;

    @Column(name = "date_end_contract", nullable = true)
    private Date dateEndContract;

    @ManyToMany(mappedBy = "employes")
    private Set<Rapport> rapports = new HashSet<>();

    @OneToMany(
            mappedBy = "employe",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<WorkingSession> workingSessions;


    public Employe() {
        super();
    }

    public Employe(String lastName,
                   String firstName,
                   Date dateBirth,
                   String email,
                   String mobile,
                   String typeContratct,
                   Date dateBeginContract,
                   Date dateEndContract,
                   Structure structure,
                   Compte compte) {
        super(lastName, firstName, dateBirth, email, mobile,structure,compte);
        this.typeContract = typeContratct;
        this.dateBeginContract = dateBeginContract;
        this.dateEndContract = dateEndContract;
    }

    public void setTypeContract(String typeContract) {
        this.typeContract = typeContract;
    }

    public Date getDateBeginContract() {
        return dateBeginContract;
    }

    public void setDateBeginContract(Date dateBeginContract) {
        this.dateBeginContract = dateBeginContract;
    }

    public Date getDateEndContract() {
        return dateEndContract;
    }
    public String getTypeContract() {
        return typeContract;
    }

    public void setDateEndContract(Date dateEndContract) {
        this.dateEndContract = dateEndContract;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", dateBirth=" + getDateBirth() +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                ", email='" + getEmail() + '\'' +
                ", mobile='" + getMobile() + '\'' +
                "structure = " + this.getStructure()+
                "compte = " +this.getCompte()+
                "typeContract='" + typeContract + '\'' +
                ", dateBeginContract=" + dateBeginContract +
                ", dateEndContract=" + dateEndContract +
                '}';
    }
}
