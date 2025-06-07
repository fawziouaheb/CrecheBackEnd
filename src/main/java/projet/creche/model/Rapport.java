package projet.creche.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Cette class représente les informations d'un rapport
 */
@Entity ( name="Rapport")
@Table ( name="rapport")
public class Rapport {

    @Id
    @SequenceGenerator(
            name = "rapport_sequance",
            sequenceName = "rapport_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "rapport_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "rapport_body", nullable = false, columnDefinition = "TEXT")
    private String bodyRapport;

    @Column(name ="rapport_date_created",nullable = false)
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "rapport_date_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @Column(name = "rapport_date", nullable = false)
    private Date rapportDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "rapport_personne",
            joinColumns = @JoinColumn(name = "rapport_id"),
            inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    private Set<Employe> employes = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_id")
    private Structure structure;


    public Rapport(String bodyRapport,Date rapportDate ,Structure structure) {
        this.bodyRapport = bodyRapport;
        this.rapportDate = rapportDate;
        this.structure = structure;
    }

    public Rapport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBodyRapport() {
        return bodyRapport;
    }

    public void setBodyRapport(String bodyRapport) {
        this.bodyRapport = bodyRapport;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Date getRapportDate() {
        return rapportDate;
    }

    public void setRapportDate(Date rapportDate) {
        this.rapportDate = rapportDate;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "id=" + id +
                ", bodyRapport='" + bodyRapport + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", employes=" + employes +
                ", structure=" + structure +
                '}';
    }
}
