package projet.creche.mapper;

import projet.creche.dto.CompteDto;
import projet.creche.model.Compte;
import projet.creche.tools.Generate;

public class CompteMapper {
    /**
     * mapper des comptes en DTO
     * @param compte le model à mapper
     * @return le DTO mappé
     */
    public static CompteDto mappeEntityToDto(Compte compte) {
        return new CompteDto(
                compte.getId(),
                compte.getStatus(),
                Generate.DateToString(compte.getUpdatedAt()),
                Generate.DateToString(compte.getLastConnexion()),
                compte.isPasswordChanged(),
                RoleMapper.mappeEntityToDto(compte.getRole())
        );
    }

}
