package projet.creche.dto.inscription.preinscription.acteurs;

import projet.creche.configs.EnumInscription.EnumPreInscription;

public class HoraireDto {
    private Long id;
    private EnumPreInscription.JourSemaine jour;
    private String heureDebut; // Format HH:mm
    private String heureFin;   // Format HH:mm

    public HoraireDto() {}

    public HoraireDto(EnumPreInscription.JourSemaine jour, String heureDebut, String heureFin) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
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

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "HoraireDto{" +
                "id=" + id +
                ", jour=" + jour +
                ", heureDebut='" + heureDebut + '\'' +
                ", heureFin='" + heureFin + '\'' +
                '}';
    }
}
