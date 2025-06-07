package projet.creche.dto;

import projet.creche.model.Compte;
import projet.creche.model.File;
import projet.creche.model.Structure;

import java.util.List;


/**
 * @author Fawzi Ouaheb
 */
public class EmployeDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String dateBirth;
    private String mobile;
    private String typeContratct;
    private String dateBeginContract;
    private String dateEndContract;
    private Structure structure;
    private Compte compte;
    private Long structureId;
    private Long compteId;
    private List<File> files;
    private String StructureName;
    private String statut;
    // Constructeur par défaut
    public EmployeDto() {}

    // Constructeur avec tous les paramètres
    public EmployeDto(Long id, String lastName, String firstName, String email, String dateBirth, String mobile,
                      Long structureId, Long compteId, String typeContratct,
                      String dateBeginContract, String dateEndContract,String statut, List<File> files) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.mobile = mobile;
        this.structureId = structureId;
        this.compteId = compteId;
        this.typeContratct = typeContratct;
        this.dateBeginContract = dateBeginContract;
        this.dateEndContract = dateEndContract;
        this.files = files;
        this.statut = statut;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public Long getcompteId() {
        return compteId;
    }

    public void setcompteId(Long compteId) {
        this.compteId = compteId;
    }

    public String getTypeContratct() {
        return typeContratct;
    }

    public void setTypeContratct(String typeContratct) {
        this.typeContratct = typeContratct;
    }

    public String getDateBeginContract() {
        return dateBeginContract;
    }

    public void setDateBeginContract(String DateBeginContract) {
        this.dateBeginContract = DateBeginContract;
    }

    public String getDateEndContract() {
        return dateEndContract;
    }

    public void setDateEndContract(String DateEndContract) {
        this.dateEndContract = DateEndContract;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<File> getFiles() {
        return files;
    }

    public String getStructureName() {
        return StructureName;
    }

    public void setStructureName(String structureName) {
        StructureName = structureName;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public static boolean isValid(EmployeDto employeDto) {
        if (employeDto == null) {
            return false;
        }

        if (employeDto.getLastName() == null || employeDto.getLastName().trim().isEmpty()) {
            return false;
        }
        if (employeDto.getFirstName() == null || employeDto.getFirstName().trim().isEmpty()) {
            return false;
        }
        if (employeDto.getEmail() == null || employeDto.getEmail().trim().isEmpty() || !employeDto.getEmail().contains("@")) {
            return false;
        }
        if (employeDto.getDateBirth() == null ) {
            return false;
        }
        if (employeDto.getMobile() == null || employeDto.getMobile().trim().isEmpty() || !employeDto.getMobile().matches("[0-9]{10}")) { // Validation du format du mobile
            return false;
        }
        if (employeDto.getStructureId() == null) {
            return false;
        }

        if (employeDto.getTypeContratct() == null || employeDto.getTypeContratct().trim().isEmpty()) {
            return false;
        }
        if (employeDto.getDateBeginContract() == null) {
            return false;
        }
        if (employeDto.getDateEndContract() == null &&
                !employeDto.getTypeContratct().equalsIgnoreCase("cdi")) {
            return false;
        }

        return true;
    }
    // Méthode toString pour afficher les informations de l'employé
    @Override
    public String toString() {
        return "EmployeDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", mobile='" + mobile + '\'' +
                ", compteId=" + compteId +
                ", typeContratct='" + typeContratct + '\'' +
                ", DateBeginContract=" + dateBeginContract +
                ", DateEndContract=" + dateEndContract +
                "le id de la structure = "+this.structureId+
                "les fichies = "+this.getFiles()+
                '}';
    }
}
