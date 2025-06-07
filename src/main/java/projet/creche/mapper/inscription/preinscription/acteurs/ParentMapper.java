package projet.creche.mapper.inscription.preinscription.acteurs;

import projet.creche.dto.inscription.preinscription.acteurs.ParentPreinscriptionDto;
import projet.creche.model.inscription.acteurs.ParentPreinscription;

import java.util.Objects;

public class ParentMapper {
    private static ParentMapper instance;

    private ParentMapper() {
    }

    public static ParentMapper getInstance() {
        if (instance == null) {
            synchronized (ParentMapper.class) {
                if (instance == null) {
                    instance = new ParentMapper();
                }
            }
        }
        return instance;
    }

    // Méthodes de mappage

    /**
     * Mappe un objet Parent vers un ParentDTO.
     *
     * @param parent l'objet Parent
     * @return ParentDTO l'objet mappé
     */
    public ParentPreinscriptionDto toParentDto(ParentPreinscription parent) {
        if (Objects.isNull(parent)) {
            return null;
        }
        ParentPreinscriptionDto dto = new ParentPreinscriptionDto();
        dto.setId(parent.getId());
        dto.setFirstName(parent.getFirstName());
        dto.setLastName(parent.getLastName());
        dto.setDateNaissance(parent.getDateNaissance());
        dto.setAdresse(parent.getAdresse());
        dto.setTelephonePortable(parent.getTelephonePortable());
        dto.setTelephoneDomicile(parent.getTelephoneDomicile());
        dto.setEmail(parent.getEmail());
        dto.setProfession(parent.getProfession());
        return dto;
    }

    /**
     * Mappe un objet ParentDTO vers un Parent.
     *
     * @param dto l'objet ParentDto
     * @return Parent l'objet mappé
     */
    public ParentPreinscription toParent(ParentPreinscriptionDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        ParentPreinscription parent = new ParentPreinscription();
        parent.setId(dto.getId());
        parent.setFirstName(dto.getFirstName());
        parent.setLastName(dto.getLastName());
        parent.setDateNaissance(dto.getDateNaissance());
        parent.setAdresse(dto.getAdresse());
        parent.setTelephonePortable(dto.getTelephonePortable());
        parent.setTelephoneDomicile(dto.getTelephoneDomicile());
        parent.setEmail(dto.getEmail());
        parent.setProfession(dto.getProfession());
        return parent;
    }
}
