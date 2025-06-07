package projet.creche.mapper;

import projet.creche.configs.ApplicationStatus;
import projet.creche.dto.CandidateDto;
import projet.creche.model.Candidate;
import projet.creche.model.Structure;
import projet.creche.tools.Generate;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CandidateMapper {


    /**
     * cette methode est static est permet de mapper les models récuperés depuis la base
     *  des données vers des DOTs
     * @param entity le model récuperé depuis la bdd.
     * @return CandidateDto
     */
    public static CandidateDto mappeEntityToDto(Candidate entity) {
        return new CandidateDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getMobile(),
                entity.getStatut(),
                entity.getCreatedAt(),
                entity.getcv(),
                entity.getMotivation(),
                entity.getStructures(),
                entity.getCity(),
                entity.getContract(),
                Generate.DateToString(entity.getDateFree())
        );
    }

    public static Candidate mappeDtoToEntity(CandidateDto dto) {
        return new Candidate(dto.getFirstName(),
                        dto.getLastName(),
                        dto.getEmail(),
                        dto.getMobile(),
                        ApplicationStatus.IN_PROGRESS.getDescription(),
                        dto.getCV(),
                        dto.getMotivation(),
                        new HashSet<>(),
                        dto.getCity(),
                        dto.getContract(),
                        Generate.StringToDate(dto.getDateFree()));
    }

    public static List<CandidateDto> mappeListEntityToListDtos(List<Candidate> entities) {
        return entities.stream()
                .map(CandidateMapper::mappeEntityToDto)
                .collect(Collectors.toList());
    }

}
