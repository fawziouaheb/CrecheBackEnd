package projet.creche.service;

import projet.creche.model.Child;
import projet.creche.model.Employe;
import projet.creche.model.Parent;

import java.util.List;

public interface PersonService {
    List<Parent>getAllParentsByStructure(String structureName);
    Parent findParentById(Long id);
    Parent findParentByName(String name);
    Parent findParentByEmail(String email);
    Parent persistParent(Parent parent);
    List<Parent> findAllParents();
    List<Employe> getAllEmployeByStructure(String structureName);
    Child findChildById(Long id);
    Child persistChild(Child child);
    List<Child>  saveAll(List<Child> children);
    Employe findEmployeByEMail(String email);
    Employe findEmployeById(Long id);
    void persistEmploye(Employe employe);
    List<Employe> getAllEmploye();
}
