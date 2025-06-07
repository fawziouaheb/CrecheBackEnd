package projet.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import projet.creche.resetAPI.CandidateRestController;

import java.sql.Date;
import java.util.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Ce modèle contient les informations liées à une structure "une mini crèche".
 * 
 * @author Fawzi Ouaheb
 */
@Entity(name = "Structure")
@Table(name = "structure")
public class Structure {

    @Id
    @SequenceGenerator(name = "structure_sequence", sequenceName = "structure_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "structure_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "structure_name", columnDefinition = "VARCHAR(60)", nullable = false)
    private String structureName;

    @Column(name = "capacity", nullable = false, columnDefinition = "INTEGER")
    private int capacity;

    @Column(name = "adresse", nullable = false, columnDefinition = "TEXT")
    private String adresse;

    @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(10)")
    private String mobile;

    @Column(name = "statut", nullable = false, columnDefinition = "VARCHAR(20)")
    private String statut;

    @Column(name = "log", nullable = false, columnDefinition = "float")
    private float log;

    @Column(name = "lat", nullable = false, columnDefinition = "float")
    private float lat;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "avantages", columnDefinition = "TEXT")
    private String avantages;

    @OneToMany(mappedBy = "structure", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, // Supprime aussi les
                                                                                              // "Person" associées
            orphanRemoval = true)
    private Set<Person> person = new HashSet<>();

    @OneToMany(mappedBy = "structure", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, // Supprime aussi les
                                                                                              // rapports associées
            orphanRemoval = true)
    private Set<Rapport> rapports = new HashSet<>();
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "structure_images", joinColumns = @JoinColumn(name = "structure_id"))
    @Column(name = "image_url")
    private List<String> images;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "candidat_structure", joinColumns = @JoinColumn(name = "structure_id"), inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private Set<Candidate> candidates = new HashSet<>();

    @OneToMany(mappedBy = "structure", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Person> parents = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    public void setId(Long id) {
        this.id = id;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public City getCity() {
        return this.city;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public float getLog() {
        return log;
    }

    public void setLog(float log) {
        this.log = log;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Person> getPerson() {
        return person;
    }

    public void setPerson(Set<Person> person) {
        this.person = person;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Structure structure = (Structure) o;
        return capacity == structure.capacity &&
                Float.compare(structure.log, log) == 0 &&
                Float.compare(structure.lat, lat) == 0 &&
                Objects.equals(id, structure.id) &&
                Objects.equals(structureName, structure.structureName) &&
                Objects.equals(adresse, structure.adresse) &&
                Objects.equals(mobile, structure.mobile) &&
                Objects.equals(statut, structure.statut) &&
                Objects.equals(createdAt, structure.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, structureName, capacity, adresse, mobile, statut, log, lat, createdAt);
    }

    public Structure(String structureName, int capacity, String adresse, String mobile, String statut, float log,
            float lat, String avantages, String description, List<String> images) {
        this.structureName = structureName;
        this.capacity = capacity;
        this.adresse = adresse;
        this.mobile = mobile;
        this.statut = statut;
        this.log = log;
        this.lat = lat;
        this.avantages = avantages;
        this.description = description;
        this.images = images;
    }

    public Structure() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }


    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }





    @Override
    public String toString() {
        return "Structure{" +
                "id=" + id +
                ", structureName='" + structureName + '\'' +
                ", capacity=" + capacity +
                ", adresse='" + adresse + '\'' +
                ", mobile='" + mobile + '\'' +
                ", statut='" + statut + '\'' +
                ", log=" + log +
                ", lat=" + lat +
                ", createdAt=" + createdAt +
                ", avantages='" + avantages + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", le nombre de candidats=" + candidates.size() +
                '}';
    }

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.candidates = candidates;
    }
}