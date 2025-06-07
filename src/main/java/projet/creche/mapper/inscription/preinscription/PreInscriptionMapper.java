package projet.creche.mapper.inscription.preinscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projet.creche.dto.inscription.preinscription.PreinscriptionDto;
import projet.creche.mapper.inscription.preinscription.acteurs.FrereSoeurMapper;
import projet.creche.mapper.inscription.preinscription.acteurs.HoraireMapper;
import projet.creche.mapper.inscription.preinscription.acteurs.ParentMapper;
import projet.creche.model.Structure;
import projet.creche.model.inscription.PreInscription;
import projet.creche.service.StructureService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PreInscriptionMapper {

    private final StructureService structureService;

    @Autowired
    public PreInscriptionMapper(StructureService structureService) {
        this.structureService = structureService;
    }

    /**
     * Mappe un objet PreInscription vers un PreInscriptionDto.
     *
     * @param preInscription l'objet PreInscription
     * @return PreinscriptionDto l'objet mappé
     */
    public PreinscriptionDto toPreinscriptionDto(PreInscription preInscription) {
        if (Objects.isNull(preInscription)) {
            return null;
        }

        PreinscriptionDto preinscriptionDto = new PreinscriptionDto();
        preinscriptionDto.setId(preInscription.getId());
        preinscriptionDto.setEnfantNee(preInscription.isEnfantNee());

        if (preInscription.isEnfantNee()) {
            preinscriptionDto.setDateNaissance(preInscription.getDateNaissance());
        } else {
            preinscriptionDto.setDateNaissancePrevue(preInscription.getDateNaissancePrevue());
        }

        preinscriptionDto.setDatePrevueEntreeCreche(preInscription.getDatePrevueEntreeCreche());
        preinscriptionDto.setFirstName(preInscription.getFirstName());
        preinscriptionDto.setLastName(preInscription.getLastName());
        preinscriptionDto.setSexe(preInscription.getSexe());
        preinscriptionDto.setSituationFamille(preInscription.getSituationFamille());
        preinscriptionDto.setInformationsComplementaires(preInscription.getInformationsComplementaires());
        preinscriptionDto.setGardeEnfant(preInscription.getGardeEnfant());

        preinscriptionDto.setParents(
                preInscription.getParents()
                        .stream()
                        .map(ParentMapper.getInstance()::toParentDto)
                        .toList()
        );

        preinscriptionDto.setFreresSoeurs(
                preInscription.getFreresSoeurs()
                        .stream()
                        .map(FrereSoeurMapper.getInstance()::toFrereSoeurDto)
                        .toList()
        );

        preinscriptionDto.setJoursChoisis(preInscription.getJoursChoisis());

        preinscriptionDto.setHoraires(
                preInscription.getHoraires()
                        .stream()
                        .map(HoraireMapper.getInstance()::toHoraireDto)
                        .collect(Collectors.toSet())
        );

        preinscriptionDto.setNombreHeuresParSemaine(preInscription.getNombreHeuresParSemaine());
        preinscriptionDto.setStructureName(preInscription.getStructure().getStructureName());

        return preinscriptionDto;
    }

    /**
     * Mappe un objet PreInscriptionDto vers un PreInscription.
     *
     * @param preinscriptionDto l'objet PreinscriptionDto
     * @return PreInscription l'objet mappé
     */
    public PreInscription toPreinscription(PreinscriptionDto preinscriptionDto) {
        if (Objects.isNull(preinscriptionDto)) {
            return null;
        }

        PreInscription preInscription = new PreInscription();

        preInscription.setEnfantNee(preinscriptionDto.isEnfantNee());
        if (preinscriptionDto.isEnfantNee()) {
            preInscription.setDateNaissance(preinscriptionDto.getDateNaissance());
        } else {
            preInscription.setDateNaissancePrevue(preinscriptionDto.getDateNaissancePrevue());
        }

        preInscription.setDatePrevueEntreeCreche(preinscriptionDto.getDatePrevueEntreeCreche());
        preInscription.setFirstName(preinscriptionDto.getFirstName());
        preInscription.setLastName(preinscriptionDto.getLastName());
        preInscription.setSexe(preinscriptionDto.getSexe());
        preInscription.setSituationFamille(preinscriptionDto.getSituationFamille());
        preInscription.setGardeEnfant(preinscriptionDto.getGardeEnfant());
        preInscription.setInformationsComplementaires(preinscriptionDto.getInformationsComplementaires());

        preInscription.setParents(
                preinscriptionDto.getParents()
                        .stream()
                        .map(ParentMapper.getInstance()::toParent)
                        .toList()
        );

        preInscription.setFreresSoeurs(
                preinscriptionDto.getFreresSoeurs()
                        .stream()
                        .map(FrereSoeurMapper.getInstance()::toFrereSoeur)
                        .toList()
        );

        preInscription.setJoursChoisis(preinscriptionDto.getJoursChoisis());

        preInscription.setHoraires(
                preinscriptionDto.getHoraires()
                        .stream()
                        .map(HoraireMapper.getInstance()::toHoraire)
                        .collect(Collectors.toSet())
        );

        preInscription.setNombreHeuresParSemaine(preinscriptionDto.getNombreHeuresParSemaine());

        // Utilisation de structureService pour trouver la structure
        String structureName = preinscriptionDto.getStructureName();
        List<Structure> structures = structureService.findByStructureName(structureName);
        if (!structures.isEmpty()) {
            preInscription.setStructure(structures.get(0));
        }

        return preInscription;
    }
}
