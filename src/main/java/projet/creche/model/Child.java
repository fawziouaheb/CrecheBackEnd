package projet.creche.model;

import jakarta.persistence.*;
import projet.creche.model.inscription.acteurs.Horaire;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private Date dateBirth;

    @Column(nullable = false)
    private Date entryDate;


    @Column(nullable = false)
    private String Genre;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @Column(columnDefinition = "TEXT")
    private String daysOfCare;

    @Column(columnDefinition = "TEXT")
    private String formula;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Horaire> horaires = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    public Child() {}

    public Child(String lastName, String firstName, Date dateBirth, String observation, Parent parent, List<String> daysOfCare, String formula, Date entryDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateBirth = dateBirth;
        this.observation = observation;
        this.parent = parent;
        this.daysOfCare = convertListToString(daysOfCare);
        this.formula = formula;
        this.entryDate = entryDate;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getObservation() {
        return observation;
    }

    public Parent getParent() {
        return parent;
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

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
    public String getGenre() {
        return Genre;
    }
    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getFormula() {
        return formula;
    }
    public void setFormula(String formula) {
        this.formula = formula;
    }
    public void setDaysOfCare(List<String> daysOfCare) {
        this.daysOfCare = convertListToString(daysOfCare);  // Convertir la liste en chaîne
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setDaysOfCare(String daysOfCare) {
        this.daysOfCare = daysOfCare;
    }

    public List<String> getDaysOfCare() {
        return convertStringToList(daysOfCare);  // Convertir la chaîne en liste
    }

    public Set<Horaire> getHoraires() {
        return horaires;
    }

    public void setHoraires(Set<Horaire> horaires) {
        this.horaires = horaires;
    }

    // Méthode pour convertir la liste en chaîne
    private String convertListToString(List<String> list) {
        return list != null ? String.join(",", list) : "";
    }

    // Méthode pour convertir la chaîne en liste
    private List<String> convertStringToList(String str) {
        return (str != null && !str.isEmpty()) ? Arrays.asList(str.split(",")) : List.of();
    }


}
