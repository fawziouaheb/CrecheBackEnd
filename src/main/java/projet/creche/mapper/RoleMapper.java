package projet.creche.mapper;

import projet.creche.dto.RoleDto;
import projet.creche.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 *  Cette classe permet de mappqer les models vers les entités OU les entités vers les models
 * @author Fawzi Ouaheb
 */
public class RoleMapper {

    /**
     * cette methode permet de mapper les Entités  vers des dto
     * @param entity une entité role
     * @return RoleDto le dto générer à partir de l'entité
     */
    public static RoleDto mappeEntityToDto(Role entity) {
        return  new RoleDto(
                entity.getRoleName().name(),
                entity.getRoleDescription(),
                entity.getAccessLevel()

        );
    }

    /**
     * cette méthode permet de mapper une liste d'entités vers une liste de DTO
     * @param entities d'entités
     * @return list une liste de Dtorole
     */
    public static List<RoleDto> mapperEntityListToDtoList(List<Role> entities) {
        List<RoleDto> dtos = new ArrayList<RoleDto>();
        for (Role entity : entities) {
            dtos.add(mappeEntityToDto(entity));
        }
        return dtos;
    }
}
