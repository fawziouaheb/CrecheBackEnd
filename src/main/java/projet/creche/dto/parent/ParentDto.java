package projet.creche.dto.parent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import projet.creche.configs.enums.RoleType;
import projet.creche.dto.RoleDto;
import projet.creche.model.File;
import projet.creche.model.Role;

import java.util.List;

public class ParentDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String dateBirth;
    private String mobile;
    private String address;
    private String profession;
    private String familySituation;

    private String secondParentLastName;
    private String secondParentFirstName;
    private String secondParentEmail;
    private String secondParentMobile;
    private String secondParentProfession;
    private String secondParentFamilySituation;

    private Long structureId;
    private Long repositoryId;
    private List<ChildDto> children;
    private List<File> files;

    public ParentDto() {}

    
    public ParentDto(Long id, String lastName, String firstName, String email, String dateBirth, String mobile,String address,
                     String secondParentLastName, String secondParentFirstName, String secondParentEmail, String secondParentMobile, List<ChildDto> children,String profession, String familySituation, String secondParentProfession, String secondParentFamilySituation, List<File> files) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateBirth = dateBirth;
        this.mobile = mobile;
        this.address = address;
        this.secondParentLastName = secondParentLastName;
        this.secondParentFirstName = secondParentFirstName;
        this.secondParentEmail = secondParentEmail;
        this.secondParentMobile = secondParentMobile;
        this.children = children;
        this.profession = profession;
        this.familySituation = familySituation;
        this.secondParentProfession = secondParentProfession;
        this.secondParentFamilySituation = secondParentFamilySituation;
        this.files = files;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSecondParentLastName(String secondParentLastName) {
        this.secondParentLastName = secondParentLastName;
    }

    public void setSecondParentEmail(String secondParentEmail) {
        this.secondParentEmail = secondParentEmail;
    }

    public void setSecondParentFirstName(String secondParentFirstName) {
        this.secondParentFirstName = secondParentFirstName;
    }

    public void setSecondParentMobile(String secondParentMobile) {
        this.secondParentMobile = secondParentMobile;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public void setChildren(List<ChildDto> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSecondParentFirstName() {
        return secondParentFirstName;
    }

    public String getSecondParentLastName() {
        return secondParentLastName;
    }

    public String getSecondParentEmail() {
        return secondParentEmail;
    }

    public String getSecondParentMobile() {
        return secondParentMobile;
    }

    public Long getStructureId() {
        return structureId;
    }

    public List<ChildDto> getChildren() {
        return children;
    }


    public Long getRepositoryId() {
        return repositoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public String getSecondParentProfession() {
        return secondParentProfession;
    }

    public void setSecondParentProfession(String secondParentProfession) {
        this.secondParentProfession = secondParentProfession;
    }

    public String getSecondParentFamilySituation() {
        return secondParentFamilySituation;
    }

    public void setSecondParentFamilySituation(String secondParentFamilySituation) {
        this.secondParentFamilySituation = secondParentFamilySituation;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "ParentDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", secondParentLastName='" + secondParentLastName + '\'' +
                ", secondParentFirstName='" + secondParentFirstName + '\'' +
                ", secondParentEmail='" + secondParentEmail + '\'' +
                ", secondParentMobile='" + secondParentMobile + '\'' +
                ", structureId=" + structureId +
                ", repositoryId=" + repositoryId +
                ", children=" + children +
                ", files=" + files +
                '}';
    }
}
