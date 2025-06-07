package projet.creche.service.impl;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.creche.configs.CompteStatus;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.Compte;
import projet.creche.model.Parent;
import projet.creche.model.Person;
import projet.creche.model.Role;
import projet.creche.repository.CompteRepository;
import projet.creche.repository.ParentRepository;
import projet.creche.repository.RoleRepository;
import projet.creche.service.CompteService;
import projet.creche.tools.Generate;

import java.util.Date;
import java.util.Random;

@Service
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ParentRepository parentRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository compteRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ParentRepository parentRepository) {
        this.compteRepository = compteRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.parentRepository = parentRepository;
    }

    @Override
    public Compte findById(Long compteId) {
        return compteRepository.findById(compteId).orElse(null);
    }

    @Override
    public Compte save(Compte compte) {
        return this.compteRepository.save(compte);
    }

    @Override
    public Compte generateCompte() {
        Faker faker = new Faker();

        // Création de la Person associée
        Person person = new Person();
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setDateBirth(new java.sql.Date(faker.date().birthday().getTime())); // Convertit en SQL Date
        person.setEmail(faker.internet().emailAddress());
        person.setMobile(faker.phoneNumber().subscriberNumber(10));

        // Création du compte
        Compte compte = new Compte();
        compte.setStatus(CompteStatus.ENABLE.getDescription());

        // Définir le username comme étant l'email de la Person
        compte.setUsername("compteAdmin");

        // Génération et encodage du mot de passe
        String rawPassword = "famo2025";
        compte.setPassword(passwordEncoder.encode(rawPassword));

        // Récupération du rôle Admin depuis la base de données
        Role adminRole = roleRepository.findByRoleName(RoleType.ADMIN)
                .orElseThrow(() -> new EntityNotFoundException("Rôle ADMIN non trouvé"));


        compte.setRole(adminRole);
        compte.setPerson(person);

        // Association bidirectionnelle
        person.setCompte(compte);

        // Sauvegarde du compte en base
        return compteRepository.save(compte);
    }

    /**
     * @return Génération du compte pour les parents
     */
    @Override
    public Compte createCompteParent(Parent parent) {
        parentRepository.save(parent);

        // Création du compte
        Compte compte = new Compte();
        compte.setStatus(CompteStatus.ENABLE.getDescription());

        // Définir le username comme étant l'email de la Person
        compte.setUsername(parent.getEmail());

        // Génération et encodage du mot de passe
        String rawPassword = Generate.password();
        compte.setPassword(passwordEncoder.encode(rawPassword));

        // Récupération du rôle Admin depuis la base de données
        Role adminRole = roleRepository.findByRoleName(RoleType.PARENT)
                .orElseThrow(() -> new EntityNotFoundException("Rôle PARENT non trouvé"));


        compte.setRole(adminRole);
        compte.setPerson(parent);

        // Association bidirectionnelle
        parent.setCompte(compte);

        // Sauvegarde du compte en base
        Compte CompteSauvegarde = compteRepository.save(compte);

        Compte compteTemp = new Compte();
        compteTemp.setPassword(rawPassword);
        compteTemp.setUsername(compte.getUsername());

        return compteTemp;
    }

}
