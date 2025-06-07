package projet.creche.model.inscription.acteurs;

import jakarta.persistence.*;
import projet.creche.configs.EnumInscription.EnumPreInscription;
import projet.creche.model.Child;
import projet.creche.model.inscription.PreInscription;

import java.sql.Time;

@Entity
@Table(name = "horaire")
public class Horaire {
    @ManyToOne
    @JoinColumn(name = "preinscription_id", nullable = true)
    private PreInscription preInscription;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = true)
    private Child child;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jour", nullable = false)
    private EnumPreInscription.JourSemaine jour;

    @Column(name = "heure_arrivee", nullable = false)
    private Time heureArrivee;

    @Column(name = "heure_depart", nullable = false)
    private Time heureDepart;

    public Horaire() {}

    public Horaire(EnumPreInscription.JourSemaine jour, Time heureArrivee, Time heureDepart) {
        this.jour = jour;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumPreInscription.JourSemaine getJour() {
        return jour;
    }

    public void setJour(EnumPreInscription.JourSemaine jour) {
        this.jour = jour;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }
    public PreInscription getPreInscription() {
        return preInscription;
    }
    public void setPreInscription(PreInscription preInscription) {
        this.preInscription = preInscription;
    }
    public Child getChild() {
        return child;
    }
    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", jour=" + jour +
                ", heureArrivee=" + heureArrivee +
                ", heureDepart=" + heureDepart +
                '}';
    }
}
