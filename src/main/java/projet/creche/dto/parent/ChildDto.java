package projet.creche.dto.parent;

import projet.creche.dto.inscription.preinscription.acteurs.HoraireDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChildDto {
    private String lastName;
    private String firstName;
    private String observation;
    private String genre;
    private String date_birth_child;
    private String entryDate;
    private List<String> daysOfCare;
    private String formula;
    private List<HoraireDto> horaires = new ArrayList<>();
    public ChildDto() {}

    public ChildDto(String lastName, String firstName, String observation,String genre, String date_birth_child, List<String> daysOfCare, String formula,String entryDate, List<HoraireDto> horaires) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.observation = observation;
        this.genre = genre;
        this.date_birth_child = date_birth_child;
        this.daysOfCare = daysOfCare;
        this.formula = formula;
        this.entryDate = entryDate;
        this.horaires = horaires;
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


    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public void setDate_birth_child(String date_birth_child) {
        this.date_birth_child = date_birth_child;
    }

    public String getDate_birth_child() {
        return date_birth_child;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getDaysOfCare() {
        return daysOfCare;
    }

    public void setDaysOfCare(List<String> daysOfCare) {
        this.daysOfCare = daysOfCare;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public List<HoraireDto> getHoraires() {
        return horaires;
    }

    public void setHoraires(List<HoraireDto> horaires) {
        this.horaires = horaires;
    }

    @Override
    public String toString() {
        return "ChildDto{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", observation='" + observation + '\'' +
                ", genre='" + this.genre + '\'' +
                ", date_birth_child='" + date_birth_child + '\'' +
                '}';
    }
}
