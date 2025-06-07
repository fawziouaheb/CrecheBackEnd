package projet.creche.dto;

import projet.creche.model.Structure;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class RapportDto {

    private Long id;

    private String rapportBody;

    private Date rapportDate;

    private LocalDateTime rapportDateUpdate;

    private Long structureId;

    private Structure structure;

    private Set<String> nameEmployes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRapportBody() {
        return rapportBody;
    }

    public void setRapportBody(String rapportBody) {
        this.rapportBody = rapportBody;
    }

    public Date getRapportDate() {
        return rapportDate;
    }

    public void setRapportDate(Date rapportDate) {
        this.rapportDate = rapportDate;
    }

    public LocalDateTime getRapportDateUpdate() {
        return rapportDateUpdate;
    }

    public void setRapportDateUpdate(LocalDateTime rapportDateUpdate) {
        this.rapportDateUpdate = rapportDateUpdate;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public Set<String> getNameEmployes() {
        return nameEmployes;
    }

    public void setNameEmployes(Set<String> nameEmployes) {
        this.nameEmployes = nameEmployes;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public RapportDto(Long id, String rapportBody, Date rapportDate, LocalDateTime rapportDateUpdate, Long structureId, Set<String> nameEmployes) {
        this.id = id;
        this.rapportBody = rapportBody;
        this.rapportDate = rapportDate;
        this.rapportDateUpdate = rapportDateUpdate;
        this.structureId = structureId;
        this.nameEmployes = nameEmployes;
    }

    @Override
    public String toString() {
        return "RapportDto{" +
                "id=" + id +
                ", rapportBody='" + rapportBody + '\'' +
                ", rapportDate=" + rapportDate +
                ", rapportDateUpdate=" + rapportDateUpdate +
                ", structure=" + structureId +
                ", nameEmployes=" + nameEmployes +
                '}';
    }

}
