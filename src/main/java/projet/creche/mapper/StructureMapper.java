package projet.creche.mapper;

import projet.creche.dto.CandidateDto;
import projet.creche.dto.StructureDto;
import projet.creche.model.Structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StructureMapper {

    /**
     * Convertit une entité Structure en DTO.
     * @param entity l'entité Structure
     * @return le DTO correspondant
     */
    public static StructureDto mappeEntityToDto(Structure entity) {
        return new StructureDto(
                entity.getId(),
                entity.getStructureName(),
                entity.getCapacity(),
                entity.getAdresse(),
                entity.getMobile(),
                entity.getStatut(),
                entity.getLog(),
                entity.getLat(),
                entity.getCreatedAt(),
                entity.getAvantages(),
                entity.getDescription(),
                entity.getImages(),
                new HashSet<>(CandidateMapper.mappeListEntityToListDtos(List.copyOf(entity.getCandidates()))),
                (entity.getCity() != null ? entity.getCity().getId() : null)
        );
    }

    /**
     * Convertit un DTO Structure en entité.
     * @param dto le DTO Structure
     * @return l'entité correspondante
     */
    public static Structure mappeDtoToEntity(StructureDto dto) {
        Structure structure = new Structure(
                dto.getStructureName(),
                dto.getCapacity(),
                dto.getAdresse(),
                dto.getMobile(),
                dto.getStatut(),
                dto.getLog(),
                dto.getLat(),
                dto.getAvantages(),
                dto.getDescription(),
                dto.getImages()
        );
        return structure;
    }

    /**
     * Convertit une liste d'entités Structure en une liste de DTOs.
     * @param entities la liste d'entités
     * @return la liste des DTOs correspondants
     */
    public static List<StructureDto> mapperEntityListToDtoList(List<Structure> entities) {
        List<StructureDto> dtos = new ArrayList<>();
        for (Structure entity : entities) {
            dtos.add(mappeEntityToDto(entity));
        }
        return dtos;
    }
}