package projet.creche.mapper;

import projet.creche.configs.ApplicationStatus;

import projet.creche.dto.ContactDto;

import projet.creche.model.Contact;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactDto mappeEntityToDto(Contact entity) {
        return new ContactDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getMobile(),
                entity.getMessage()

        );
    }

    public static Contact mappeDtoToEntity(ContactDto dto) {
        return new Contact(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getMobile(),
                dto.getMessage());

    }

    public static List<ContactDto> mappeListEntityToListDtos(List<Contact> entities) {
        return entities.stream()
                .map(ContactMapper::mappeEntityToDto)
                .collect(Collectors.toList());
    }
}
