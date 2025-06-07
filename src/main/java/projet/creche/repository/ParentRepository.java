package projet.creche.repository;


import projet.creche.model.Employe;
import projet.creche.model.Parent;

import java.util.List;

public interface ParentRepository extends PersonRepository<Parent> {
    Parent findByEmail(String email);
    List<Parent> findAllByStructure_StructureName(String structureName);
}
