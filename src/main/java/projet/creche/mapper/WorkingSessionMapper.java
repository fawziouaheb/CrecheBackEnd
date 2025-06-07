package projet.creche.mapper;

import projet.creche.dto.WorkingSessionDto;
import projet.creche.model.WorkingSession;

import java.util.List;
import java.util.stream.Collectors;

/**
 * mapper les session de travail entre les DTO et les entités
 * @author Fawzi Ouaheb
 */
public class WorkingSessionMapper {

    /**
     *mapper une entité de workingsession en dto
     * @param entity WorkingSession
     * @return WorkingSessionDto
     */
    public static WorkingSessionDto mappeEntityToDto(WorkingSession entity) {
        WorkingSessionDto dto = new WorkingSessionDto();
        dto.setId(entity.getId());
        dto.setArrivalTime(entity.getArrivalTime());
        dto.setDepartureTime(entity.getDepartureTime());
        dto.setDateSession(entity.getDateSession());
        dto.setDurationMinute(entity.getDurationMinute());
        dto.setTypeEnregistration(entity.getTypeEnregistration());
        dto.setEmployeId(entity.getEmploye().getId());
        return dto;
    }

    public static WorkingSession mappeDtoToEntity(WorkingSessionDto dto) {
        WorkingSession session = new WorkingSession();
        session.setId(dto.getId());
        session.setArrivalTime(dto.getArrivalTime());
        session.setDepartureTime(dto.getDepartureTime());
        session.setDateSession(dto.getDateSession());
        session.setDurationMinute(dto.getDurationMinute());
        session.setTypeEnregistration(dto.getTypeEnregistration());
        session.setEmploye(dto.getEmploye());
        return session;
    }

    public static List<WorkingSessionDto> mappeEntitiesToDtos(List<WorkingSession> entities) {
        return entities.stream()
                .map(WorkingSessionMapper::mappeEntityToDto)
                .collect(Collectors.toList());
    }
}
