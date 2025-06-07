package projet.creche.mapper.inscription.preinscription.acteurs;

import projet.creche.dto.inscription.preinscription.acteurs.FrereSoeurDto;
import projet.creche.dto.inscription.preinscription.acteurs.HoraireDto;
import projet.creche.model.inscription.acteurs.FrereSoeur;
import projet.creche.model.inscription.acteurs.Horaire;

import java.util.Objects;

public class FrereSoeurMapper {
    private static FrereSoeurMapper instance;

    private FrereSoeurMapper() {
    }

    public static FrereSoeurMapper getInstance() {
        if (instance == null) {
            synchronized (FrereSoeurMapper.class) {
                if (instance == null) {
                    instance = new FrereSoeurMapper();
                }
            }
        }
        return instance;
    }

    // Méthodes de mappage

    /**
     * Mappe un objet FrereSoeur vers un FrereSoeurDto.
     *
     * @param frereSoeur l'objet FrereSoeur
     * @return FrereSoeurDto l'objet mappé
     */
    public FrereSoeurDto toFrereSoeurDto(FrereSoeur frereSoeur) {
        if (Objects.isNull(frereSoeur)) {
            return null;
        }
        FrereSoeurDto frereSoeurDto = new FrereSoeurDto();

        frereSoeurDto.setId(frereSoeur.getId());
        frereSoeurDto.setFirstName(frereSoeur.getFirstName());
        frereSoeurDto.setLastName(frereSoeur.getLastName());
        frereSoeurDto.setDateNaissance(frereSoeur.getDateNaissance());

        return frereSoeurDto;
    }

    /**
     * Mappe un objet FrereSoeur vers un FrereSoeurDto.
     *
     * @param frereSoeurDto l'objet FrereSoeur
     * @return FrereSoeurDto l'objet mappé
     */
    public FrereSoeur toFrereSoeur(FrereSoeurDto frereSoeurDto) {
        if (Objects.isNull(frereSoeurDto)) {
            return null;
        }
        FrereSoeur frereSoeur = new FrereSoeur();

        frereSoeur.setId(frereSoeurDto.getId());
        frereSoeur.setFirstName(frereSoeurDto.getFirstName());
        frereSoeur.setLastName(frereSoeurDto.getLastName());
        frereSoeur.setDateNaissance(frereSoeurDto.getDateNaissance());

        return frereSoeur;
    }
}
