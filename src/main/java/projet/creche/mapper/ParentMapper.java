package projet.creche.mapper;
import projet.creche.dto.parent.ChildDto;
import projet.creche.dto.parent.ParentDto;
import projet.creche.mapper.inscription.preinscription.acteurs.HoraireMapper;
import projet.creche.model.*;
import projet.creche.model.inscription.acteurs.ParentPreinscription;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParentMapper {

    public static ParentDto mapEntityToDto(Parent parent) {
        List<ChildDto> childDtos = parent.getChildren().stream()
                .map(child -> new ChildDto(
                        child.getLastName(),
                        child.getFirstName(),
                        child.getObservation(),
                        child.getGenre(),
                        child.getDateBirth().toString(),
                        child.getDaysOfCare(),
                        child.getFormula(),
                        child.getEntryDate().toString(),
                        child.getHoraires().stream().map(horaire -> HoraireMapper.getInstance().toHoraireDto(horaire)).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return new ParentDto(
                parent.getId(),
                parent.getLastName(),
                parent.getFirstName(),
                parent.getEmail(),
                parent.getDateBirth().toString(),
                parent.getMobile(),
                parent.getAddress_parent(),
                parent.getSecondParentLastName(),  // Ajout de cette ligne
                parent.getSecondParentFirstName(),
                parent.getSecondParentEmail(),
                parent.getSecondParentMobile(),
                childDtos,
                parent.getProfession(),
                parent.getFamilySituation(),
                parent.getSecondParentProfession(),
                parent.getSecondParentFamilySituation(),
                parent.getFiles()
        );
    }

    public static Parent mapParentPreinscriptionToParent1(ParentPreinscription parentPreinscription){
        Parent parent = new Parent();

        parent.setFirstName(parentPreinscription.getFirstName());
        parent.setLastName(parentPreinscription.getLastName());
        parent.setDateBirth(parentPreinscription.getDateNaissance());
        parent.setAddress_parent(parentPreinscription.getAdresse());
        parent.setProfession(parentPreinscription.getProfession());
        parent.setEmail(parentPreinscription.getEmail());
        parent.setMobile(parentPreinscription.getTelephonePortable());

        return parent;
    }

    public static Parent mapParentPreinscriptionToParent2(Parent parent2, ParentPreinscription parentPreinscription){

        parent2.setSecondParentFirstName(parentPreinscription.getFirstName());
        parent2.setSecondParentLastName(parentPreinscription.getLastName());
        parent2.setSecondParentProfession(parentPreinscription.getProfession());
        parent2.setSecondParentEmail(parentPreinscription.getEmail());
        parent2.setSecondParentMobile(parentPreinscription.getTelephonePortable());

        return parent2;
    }
    public static List<ParentDto> mapEntitiesToDtos(List<Parent> parents) {
        return parents.stream()
                .map(ParentMapper::mapEntityToDto) // Utiliser ParentMapper au lieu de ParentDto
                .collect(Collectors.toList());
    }


}
