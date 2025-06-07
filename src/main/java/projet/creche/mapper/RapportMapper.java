package projet.creche.mapper;

import projet.creche.dto.RapportDto;
import projet.creche.model.Employe;
import projet.creche.model.Rapport;


import java.util.HashSet;
import java.util.Set;

public class RapportMapper {


    /**
     * cette methode permet de mapper les Entités  vers des dto
     * @param entity une entité rapport
     * @return rapportDto le dto générer à partir de l'entité
     */
    public static RapportDto mappeEntityToDto(Rapport entity) {
        Set<String> EmployeNames = new HashSet<>();
        Set<Employe> employes  = entity.getEmployes();
        for (Employe employe: employes) {
            EmployeNames.add(employe.getFirstName() + " " + employe.getLastName());
        }
        return new RapportDto(
                entity.getId(),
                entity.getBodyRapport(),
                entity.getRapportDate(),
                entity.getDateUpdated(),
                entity.getStructure().getId(),
                EmployeNames
                );
    }

    /**
     * cette méthode permet de transformer un DTO en model rapport
     * @param dto rapportDTO
     * @return le model rapport
     */
    public  static Rapport mappeDtoToEntity(RapportDto dto) {
        Set<String> EmployeNames = new HashSet<>();
        return new Rapport(dto.getRapportBody(), dto.getRapportDate(),dto.getStructure());
    }
}
