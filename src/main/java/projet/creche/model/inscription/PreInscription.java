package projet.creche.model.inscription;

import jakarta.persistence.*;
import projet.creche.configs.EnumInscription.*;
import projet.creche.model.Structure;
import projet.creche.model.inscription.acteurs.FrereSoeur;
import projet.creche.model.inscription.acteurs.Horaire;
import projet.creche.model.inscription.acteurs.ParentPreinscription;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PreInscription")
@Table(name = "preinscription")
public class PreInscription {

    @Id
    @SequenceGenerator(
            name = "preinscription_sequence",
            sequenceName = "preinscription_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "enfantNee", nullable = false)
    private boolean enfantNee;

    @Column(name = "prenom", nullable = false)
    private String firstName;

    @Column(name = "nom", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private EnumPreInscription.Sexe sexe;

    @Column(name = "dateNaissance", nullable = true)
    private Date dateNaissance;

    @Column(name = "dateNaissancePrevue", nullable = true)
    private Date dateNaissancePrevue;

    @Column(name = "datePrevueEntreeCreche", nullable = true)
    private Date datePrevueEntreeCreche;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation_famille", nullable = false)
    private EnumPreInscription.SituationFamille situationFamille;

    @Enumerated(EnumType.STRING)
    @Column(name = "garde_enfant", nullable = true)
    private EnumPreInscription.GardeEnfant gardeEnfant;

    @Column(name = "info_complementaire", nullable = true)
    private String informationsComplementaires;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preinscription_id")  // Clé étrangère vers la table 'preinscription'
    private List<FrereSoeur> freresSoeurs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preinscription_id")  // Clé étrangère vers la table 'preinscription'
    private List<ParentPreinscription> parents = new ArrayList<>();

    @ElementCollection(targetClass = EnumPreInscription.JourSemaine.class)
    @CollectionTable(name = "preinscription_jours", joinColumns = @JoinColumn(name = "preinscription_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "jour")
    private Set<EnumPreInscription.JourSemaine> joursChoisis = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre_heures_par_semaine")
    private EnumPreInscription.NombreHeuresParSemaine nombreHeuresParSemaine;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preinscription_id")
    private Set<Horaire> horaires = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "structure_id", nullable = false)
    private Structure structure;


    // Constructeurs
    public PreInscription() {
    }

    public PreInscription(boolean enfantNee, String firstName, String lastName, EnumPreInscription.Sexe sexe,
                          Date dateNaissance, Date dateNaissancePrevue, EnumPreInscription.SituationFamille situationFamille,
                          ArrayList<ParentPreinscription> parents, ArrayList<FrereSoeur> freresSoeurs,Set<EnumPreInscription.JourSemaine> joursChoisis, EnumPreInscription.NombreHeuresParSemaine nombreHeuresParSemaine,
                          Set<Horaire> horaires, Structure structure) {
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
        this.structure = structure;
    }

    // Getters et Setters
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

    public List<FrereSoeur> getFreresSoeurs() {
        return freresSoeurs;
    }

    public void setFreresSoeurs(List<FrereSoeur> freresSoeurs) {
        this.freresSoeurs = freresSoeurs;
    }

    public List<ParentPreinscription> getParents() {
        return parents;
    }

    public void setParents(List<ParentPreinscription> parents) {
        this.parents = parents;
    }

    public void setSituationFamille(EnumPreInscription.SituationFamille situationFamille) {
        this.situationFamille = situationFamille;
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

    public Set<Horaire> getHoraires() {
        return horaires;
    }

    public void setHoraires(Set<Horaire> horaires) {
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    // toString
    @Override
    public String toString() {
        return "PreInscription{" +
                "id=" + id +
                ", enfantNee=" + enfantNee +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sexe=" + sexe +
                ", dateNaissance=" + dateNaissance +
                ", dateNaissancePrevue=" + dateNaissancePrevue +
                ", Parent=" + parents.size() +
                ", Frere et Soeur=" + freresSoeurs.size() +
                ", situationFamille=" + situationFamille +
                ", joursChoisis=" + joursChoisis +
                ", nombreHeuresParSemaine=" + nombreHeuresParSemaine +
                ", horaires=" + horaires +
                ", structure=" + structure +
                '}';
    }
}
