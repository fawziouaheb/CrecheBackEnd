package projet.creche.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Cette classe DTO permet de filtrer les données provenant du modèle Structure.
 * Elle est utilisée pour le transfert de données entre le backend et le frontend.
 *
 * @author Fawzi Ouaheb
 */
public class StructureDto {

    private Long id;
    private String structureName;
    private int capacity;
    private String adresse;
    private String mobile;
    private String statut;
    private float log;
    private float lat;
    private Long cityId;
    private Date createdAt;
    private String avantages;
    private String description;
    private List<String> images;
    private Set<CandidateDto> candidates = new HashSet<>();

    public StructureDto(Long id, String structureName, int capacity, String adresse, String mobile, String statut, float log, float lat, Date createdAt, String avantages, String description, List<String> images, Set<CandidateDto> candidates, Long cityId) {
        this.id = id;
        this.structureName = structureName;
        this.capacity = capacity;
        this.adresse = adresse;
        this.mobile = mobile;
        this.statut = statut;
        this.log = log;
        this.lat = lat;
        this.createdAt = createdAt;
        this.avantages = avantages;
        this.description = description;
        this.images = images;
        this.candidates = candidates;
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "StructureDto{" +
                "id=" + id +
                ", structureName='" + structureName + '\'' +
                ", capacity=" + capacity +
                ", adresse='" + adresse + '\'' +
                ", mobile='" + mobile + '\'' +
                ", statut='" + statut + '\'' +
                ", log=" + log +
                ", lat=" + lat +
                ", cityId=" + cityId +
                ", createdAt=" + createdAt +
                ", avantages='" + avantages + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", candidates=" + candidates +
                '}';
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Set<CandidateDto> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<CandidateDto> candidates) {
        this.candidates = candidates;
    }
}
