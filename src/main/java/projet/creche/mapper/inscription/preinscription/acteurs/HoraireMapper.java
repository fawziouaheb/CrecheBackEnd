package projet.creche.mapper.inscription.preinscription.acteurs;

import projet.creche.dto.inscription.preinscription.acteurs.HoraireDto;
import projet.creche.model.inscription.acteurs.Horaire;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class HoraireMapper {

    private static HoraireMapper instance;

    private HoraireMapper() {}

    public static HoraireMapper getInstance() {
        if (instance == null) {
            synchronized (HoraireMapper.class) {
                if (instance == null) {
                    instance = new HoraireMapper();
                }
            }
        }
        return instance;
    }

    public HoraireDto toHoraireDto(Horaire horaire) {
        if (Objects.isNull(horaire)) return null;

        return new HoraireDto(
                horaire.getJour(),
                horaire.getHeureArrivee().toLocalTime().toString().substring(0, 5), // HH:mm
                horaire.getHeureDepart().toLocalTime().toString().substring(0, 5)  // HH:mm
        );
    }

    public Horaire toHoraire(HoraireDto dto) {
        if (Objects.isNull(dto)) return null;

        Horaire horaire = new Horaire();
        horaire.setId(dto.getId());
        horaire.setJour(dto.getJour());

        try {
            // Ajout automatique de ":00" si besoin
            String heureDebut = dto.getHeureDebut().length() == 5 ? dto.getHeureDebut() + ":00" : dto.getHeureDebut();
            String heureFin = dto.getHeureFin().length() == 5 ? dto.getHeureFin() + ":00" : dto.getHeureFin();

            horaire.setHeureArrivee(Time.valueOf(LocalTime.parse(heureDebut)));
            horaire.setHeureDepart(Time.valueOf(LocalTime.parse(heureFin)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Format d'heure invalide. Attendu HH:mm ou HH:mm:ss", e);
        }

        return horaire;
    }
}
