package projet.creche.dto.inscription.preinscription;

import jakarta.persistence.*;
import projet.creche.configs.EnumInscription.EnumPreInscription;
import projet.creche.dto.inscription.preinscription.acteurs.FrereSoeurDto;
import projet.creche.dto.inscription.preinscription.acteurs.HoraireDto;
import projet.creche.dto.inscription.preinscription.acteurs.ParentPreinscriptionDto;
import projet.creche.model.Structure;
import projet.creche.model.inscription.acteurs.FrereSoeur;
import projet.creche.model.inscription.acteurs.Horaire;
import projet.creche.model.inscription.acteurs.ParentPreinscription;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreinscriptionDto {
    private Long id;
    private boolean enfantNee;
    private String firstName;
    private String lastName;
    private EnumPreInscription.Sexe sexe;
    private Date dateNaissance;
    private Date dateNaissancePrevue;
    private Date datePrevueEntreeCreche;
    private EnumPreInscription.SituationFamille situationFamille;
    private EnumPreInscription.GardeEnfant gardeEnfant;
    private List<FrereSoeurDto> freresSoeurs = new ArrayList<>();
    private List<ParentPreinscriptionDto> parents = new ArrayList<>();
    private String informationsComplementaires;
    private Set<EnumPreInscription.JourSemaine> joursChoisis = new HashSet<>();
    private EnumPreInscription.NombreHeuresParSemaine nombreHeuresParSemaine;
    private Set<HoraireDto> horaires = new HashSet<>();
    private String structureName;
    public PreinscriptionDto() {
    }

    public PreinscriptionDto(boolean enfantNee, String firstName, String lastName, EnumPreInscription.Sexe sexe,
                          Date dateNaissance, Date dateNaissancePrevue, EnumPreInscription.SituationFamille situationFamille,
                          ArrayList<ParentPreinscriptionDto> parents, ArrayList<FrereSoeurDto> freresSoeurs,Set<EnumPreInscription.JourSemaine> joursChoisis, EnumPreInscription.NombreHeuresParSemaine nombreHeuresParSemaine,
                          Set<HoraireDto> horaires, String structureName) {
        this.enfantNee = enfantNee;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.dateNaissancePrevue = dateNaissancePrevue;
        this.situationFamille = situationFamille;
        this.parents = parents;
        this.freresSoeurs = freresSoeurs;
        this.joursChoisis = joursChoisis;
        this.nombreHeuresParSemaine = nombreHeuresParSemaine;
        this.horaires = horaires;
        this.structureName = structureName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnfantNee() {
        return enfantNee;
    }

    public void setEnfantNee(boolean enfantNee) {
        this.enfantNee = enfantNee;
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

    public EnumPreInscription.Sexe getSexe() {
        return sexe;
    }

    public void setSexe(EnumPreInscription.Sexe sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateNaissancePrevue() {
        return dateNaissancePrevue;
    }

    public void setDateNaissancePrevue(Date dateNaissancePrevue) {
        this.dateNaissancePrevue = dateNaissancePrevue;
    }

    public Date getDatePrevueEntreeCreche() {
        return datePrevueEntreeCreche;
    }

    public void setDatePrevueEntreeCreche(Date datePrevueEntreeCreche) {
        this.datePrevueEntreeCreche = datePrevueEntreeCreche;
    }

    public EnumPreInscription.SituationFamille getSituationFamille() {
        return situationFamille;
    }

    public void setSituationFamille(EnumPreInscription.SituationFamille situationFamille) {
        this.situationFamille = situationFamille;
    }

    public List<FrereSoeurDto> getFreresSoeurs() {
        return freresSoeurs;
    }

    public void setFreresSoeurs(List<FrereSoeurDto> freresSoeurs) {
        this.freresSoeurs = freresSoeurs;
    }

    public List<ParentPreinscriptionDto> getParents() {
        return parents;
    }

    public void setParents(List<ParentPreinscriptionDto> parents) {
        this.parents = parents;
    }

    public Set<EnumPreInscription.JourSemaine> getJoursChoisis() {
        return joursChoisis;
    }

    public void setJoursChoisis(Set<EnumPreInscription.JourSemaine> joursChoisis) {
        this.joursChoisis = joursChoisis;
    }

    public EnumPreInscription.NombreHeuresParSemaine getNombreHeuresParSemaine() {
        return nombreHeuresParSemaine;
    }

    public void setNombreHeuresParSemaine(EnumPreInscription.NombreHeuresParSemaine nombreHeuresParSemaine) {
        this.nombreHeuresParSemaine = nombreHeuresParSemaine;
    }

    public Set<HoraireDto> getHoraires() {
        return horaires;
    }

    public void setHoraires(Set<HoraireDto> horaires) {
        this.horaires = horaires;
    }

    public EnumPreInscription.GardeEnfant getGardeEnfant() {
        return gardeEnfant;
    }

    public void setGardeEnfant(EnumPreInscription.GardeEnfant gardeEnfant) {
        this.gardeEnfant = gardeEnfant;
    }

    public String getInformationsComplementaires() {
        return informationsComplementaires;
    }

    public void setInformationsComplementaires(String informationsComplementaires) {
        this.informationsComplementaires = informationsComplementaires;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }
}
