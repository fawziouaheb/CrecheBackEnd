package projet.creche.configs.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.Role;
import projet.creche.repository.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for (RoleType type : RoleType.values()) {
            roleRepository.findById(type).orElseGet(() -> roleRepository.save(new Role(type)));
        }
    }
}

