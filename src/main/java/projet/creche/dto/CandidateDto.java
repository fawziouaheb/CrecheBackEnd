package projet.creche.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projet.creche.model.Structure;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class CandidateDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String statut;
    private Date createdAt;
    private String cv;
    private String motivation;
    private String city;
    private String contract;
    private String dateFree;
    @JsonIgnore // Empêche la sérialisation des candidats dans chaque Structure
    private Set<Structure> structures = new HashSet<>();
    private Long structureId; // ID de la structure à associer

    public CandidateDto() {
    }

    public CandidateDto(Long id, String firstName, String lastName, String email, String mobile,
                        String statut, Date createdAt, String cv, String motivation, Long structureId,String city,String contract,String dateFree) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.statut = statut;
        this.createdAt = createdAt;
        this.cv = cv;
        this.motivation = motivation;
        this.structureId = structureId;
        this.city = city;
        this.contract = contract;
        this.dateFree = dateFree;
    }
    public CandidateDto(Long id, String firstName, String lastName, String email, String mobile,
                        String statut, Date createdAt, String cv, String motivation, Set<Structure> structures,String city,String contract,String dateFree) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.statut = statut;
        this.createdAt = createdAt;
        this.cv = cv;
        this.motivation = motivation;
        this.structures = structures;
        this.city = city;
        this.contract = contract;
        this.dateFree = dateFree;
    }

    // Getters et Setters

    public Set<Structure> getStructures() {
        return structures;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getContract() {
        return contract;
    }

    public void setStructures(Set<Structure> structures) {
        this.structures = structures;
    }

    public String getDateFree() {
        return dateFree;
    }

    public void setDateFree(String dateFree) {
        this.dateFree = dateFree;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCV() {
        return cv;
    }

    public void setCV(String CV) {
        this.cv = CV;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    @Override
    public String toString() {
        return "CandidateDto{" +
                "id=" + id +
                ", firstName='" +  firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", statut='" + statut + '\'' +
                ", createdAt=" + createdAt +
                ", cv='" + this.cv + '\'' +
                ", motivation='" + motivation + '\'' +
                ", structureId=" + structureId +
                '}';
    }
}
