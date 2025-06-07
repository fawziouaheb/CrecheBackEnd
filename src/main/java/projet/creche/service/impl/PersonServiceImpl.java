package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.Child;
import projet.creche.model.Employe;
import projet.creche.model.Parent;
import projet.creche.repository.ChildRepository;
import projet.creche.repository.EmployeRepository;
import projet.creche.repository.ParentRepository;
import projet.creche.repository.PersonRepository;
import projet.creche.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private ParentRepository parentRepository;
    private EmployeRepository employeRepository;
    private ChildRepository childRepository;
    @Autowired
    public PersonServiceImpl(ParentRepository parentRepository, EmployeRepository employeRepository, ChildRepository  childRepository) {
        this.parentRepository = parentRepository;
        this.employeRepository = employeRepository;
        this.childRepository = childRepository;
    }


    @Override
    public List<Parent> getAllParentsByStructure(String structureName) {
        return this.parentRepository.findAllByStructure_StructureName(structureName);
    }

    @Override
    public Parent findParentById(Long id) {
        return this.parentRepository.findById(id).orElse(null);
    }

    @Override
    public Parent findParentByName(String name) {
        return null;
    }

    @Override
    public Parent findParentByEmail(String email) {
        return this.parentRepository.findByEmail(email);
    }

    @Override
    public Parent persistParent(Parent parent) {
        this.transformDataParent(parent);
        return  this.parentRepository.save(parent);
    }

    @Override
    public List<Parent> findAllParents() {
        return this.parentRepository.findAll();
    }

    @Override
    public List<Employe> getAllEmployeByStructure(String structureName) {
        return this.employeRepository.findAllByStructure_StructureName(structureName);
    }

    @Override
    public Child findChildById(Long id) {
        return null;
    }

    @Override
    public Child persistChild(Child child) {
        return this.childRepository.save(child);
    }

    @Override
    public List<Child> saveAll(List<Child> children) {
        return this.childRepository.saveAll(children);
    }

    @Override
    public Employe findEmployeByEMail(String email) {
        return this.employeRepository.findByEmail(email);
    }

    @Override
    public Employe findEmployeById(Long id) {
        return this.employeRepository.findById(id).orElse(null);
    }

    /**
     * sauvegarder
     * @param employe
     */
    @Override
    public void persistEmploye(Employe employe) {
        this.tranformDataEmploye(employe);
        this.employeRepository.save(employe);
    }

    @Override
    public List<Employe> getAllEmploye() {
        return this.employeRepository.findAll();
    }

    private void tranformDataEmploye(Employe employe) {
        employe.setFirstName(employe.getFirstName().trim().toUpperCase());
        employe.setLastName(employe.getLastName().trim().toUpperCase());
        employe.setEmail(employe.getEmail().trim().toUpperCase());
    }

    private void transformDataParent(Parent parent) {
        parent.setFirstName(parent.getFirstName().trim().toUpperCase());
        parent.setLastName(parent.getLastName().trim().toUpperCase());
        parent.setEmail(parent.getEmail().trim().toUpperCase());
    }

}
