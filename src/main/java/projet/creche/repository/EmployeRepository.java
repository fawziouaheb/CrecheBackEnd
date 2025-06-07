package projet.creche.repository;

import projet.creche.model.Employe;

import java.util.List;

public interface EmployeRepository extends PersonRepository<Employe> {
    Employe findByEmail(String email);
    List<Employe> findAllByStructure_StructureName(String structureName);

}
