package projet.creche.mapper;

import projet.creche.dto.EmployeDto;
import projet.creche.dto.RoleDto;
import projet.creche.model.Employe;
import projet.creche.tools.Generate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeMapper {

    /**
     * Mappe un Employe entity vers un EmployeDto.
     */
    public static EmployeDto mappeEntityToDto(Employe employe) {
        if (employe == null) {
            return null;
        }

        String dateEndContract = null;
        // On ne mappe pas la date de fin de contrat si c'est un CDI
        if (employe.getTypeContract() == null || !employe.getTypeContract().equalsIgnoreCase("cdi")) {
            dateEndContract = Generate.DateToString(employe.getDateEndContract());
        }

        EmployeDto employeDto = new EmployeDto(
                employe.getId(),
                employe.getLastName(),
                employe.getFirstName(),
                employe.getEmail(),
                Generate.DateToString(employe.getDateBirth()),
                employe.getMobile(),
                employe.getStructure().getId(),
                employe.getCompte().getId(),
                employe.getTypeContract(),
                Generate.DateToString(employe.getDateBeginContract()),
                dateEndContract,
                employe.getCompte().getStatus(),// on n'affiche pas la date de fin si CDI
                employe.getFiles()
        );

        return employeDto;
    }


    public static Employe mappeDtoToEntity(EmployeDto dto) {
        if (dto == null) return null;

        // Conversion des dates
        Date dateBirth = Generate.StringToDate(dto.getDateBirth());
        Date dateBeginContract = Generate.StringToDate(dto.getDateBeginContract());
        Date dateEndContract = null;

        // On ne convertit dateEndContract que si ce n’est pas un CDI
        if (dto.getTypeContratct() != null &&
                !dto.getTypeContratct().trim().equalsIgnoreCase("cdi")) {
            dateEndContract = Generate.StringToDate(dto.getDateEndContract());
        }

        return new Employe(
                dto.getLastName(),
                dto.getFirstName(),
                dateBirth,
                dto.getEmail(),
                dto.getMobile(),
                dto.getTypeContratct(),
                dateBeginContract,
                dateEndContract,// assure-toi que c'est bien une entité JPA
                dto.getStructure(),
                dto.getCompte()
        );
    }

    /**
     * Mappe une liste d'entités Employe vers une liste de DTOs.
     */
    public static List<EmployeDto> mappeListEntityToListDtos(List<Employe> employeList) {
        if (employeList == null) return new ArrayList<>();
        return employeList.stream()
                .map(EmployeMapper::mappeEntityToDto)
                .toList();
    }
}
