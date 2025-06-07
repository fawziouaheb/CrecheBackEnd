package projet.creche.model;

import jakarta.persistence.*;
import projet.creche.model.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends Person {


    @Column(name="profession")
    private String profession;

    @Column(name="familySituation")
    private String familySituation;

    @Column(name="secondParentProfession")
    private String secondParentProfession;

    @Column(name="secondParentFamilySituation")
    private String secondParentFamilySituation;

    @Column(name = "address_parent")
    private String address_parent;

    @Column(name = "second_parent_last_name")
    private String secondParentLastName;

    @Column(name = "second_parent_first_name")
    private String secondParentFirstName;

    @Column(name = "second_parent_email")
    private String secondParentEmail;

    @Column(name = "second_parent_mobile")
    private String secondParentMobile;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    @OneToMany(
            mappedBy = "parent",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Payment> payments;

    public Parent() {
        super();
    }

    public Parent(String lastName, String firstName, Date dateBirth, String email, String mobile,
                  String secondParentLastName, String secondParentFirstName,
                  String secondParentEmail, String secondParentMobile,
                  Structure structure, Compte compte, List<Child> children,String profession,String familySituation, String secondParentProfession, String secondParentFamilySituation) {
        super(lastName, firstName, dateBirth, email, mobile, structure, compte);
        this.secondParentLastName = secondParentLastName;
        this.secondParentFirstName = secondParentFirstName;
        this.secondParentEmail = secondParentEmail;
        this.secondParentMobile = secondParentMobile;
        this.children = children;
        this.profession = profession;
        this.familySituation = familySituation;
        this.secondParentProfession = secondParentProfession;
        this.secondParentFamilySituation =secondParentFamilySituation;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getSecondParentLastName() {
        return secondParentLastName;
    }

    public void setSecondParentLastName(String secondParentLastName) {
        this.secondParentLastName = secondParentLastName;
    }

    public String getSecondParentFirstName() {
        return secondParentFirstName;
    }

    public void setSecondParentFirstName(String secondParentFirstName) {
        this.secondParentFirstName = secondParentFirstName;
    }

    public String getSecondParentEmail() {
        return secondParentEmail;
    }

    public void setSecondParentEmail(String secondParentEmail) {
        this.secondParentEmail = secondParentEmail;
    }

    public String getSecondParentMobile() {
        return secondParentMobile;
    }

    public void setSecondParentMobile(String secondParentMobile) {
        this.secondParentMobile = secondParentMobile;
    }

    public void setAddress_parent(String address_parent) {
        this.address_parent = address_parent;
    }

    public String getAddress_parent() {
        return address_parent;
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

    @Override
    public String toString() {
        return "Parent{" +
                "address_parent='" + address_parent + '\'' +
                ", secondParentLastName='" + secondParentLastName + '\'' +
                ", secondParentFirstName='" + secondParentFirstName + '\'' +
                ", secondParentEmail='" + secondParentEmail + '\'' +
                ", secondParentMobile='" + secondParentMobile + '\'' +
                ", children=" + children +
                '}';
    }
}
