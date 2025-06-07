package projet.creche.mapper;

import projet.creche.dto.ActiviteDto;
import projet.creche.model.Activite;

import java.util.List;
import java.util.stream.Collectors;

public class ActiviteMapper {

    // Méthode pour mapper une entité Activite en DTO
    public static ActiviteDto mappeEntityToDto(Activite entity) {
        if (entity == null) {
            return null;
        }
        return new ActiviteDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl()
        );
    }

    // Méthode pour mapper un DTO ActiviteDto en entité
    public static Activite mappeDtoToEntity(ActiviteDto dto) {
        return new Activite(
                dto.getName(),
                dto.getDescription(),
                dto.getImageUrl()
        );
    }

    // Méthode pour mapper une liste d'entités Activite en une liste de DTOs
    public static List<ActiviteDto> mappeListEntityToListDtos(List<Activite> entities) {
        return entities.stream()
                .map(ActiviteMapper::mappeEntityToDto)
                .collect(Collectors.toList());
    }
}
