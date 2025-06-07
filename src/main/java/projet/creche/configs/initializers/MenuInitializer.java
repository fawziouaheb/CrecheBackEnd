package projet.creche.configs.initializers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projet.creche.configs.enums.MenuDefinition;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.MenuItem;
import projet.creche.repository.MenuItemRepository;

import java.util.ArrayList;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class MenuInitializer implements CommandLineRunner {

    private final MenuItemRepository repository;

    @Override
    public void run(String... args) {
        for (MenuDefinition def : MenuDefinition.values()) {
            String key = def.name();
            repository.findByKey(key).ifPresentOrElse(
                    existing -> updateIfNeeded(existing, def),
                    () -> createNewMenuItem(key, def)
            );
        }
    }

    private void updateIfNeeded(MenuItem existing, MenuDefinition def) {
        boolean updated = false;

        // Ajout rôles manquants
        for (var role : def.roles) {
            if (!existing.getRoles().contains(role)) {
                existing.getRoles().add(role);
                updated = true;
            }
        }

        // Suppression anciens rôles
        Iterator<RoleType> it = existing.getRoles().iterator();
        while (it.hasNext()) {
            RoleType r = it.next();
            if (!def.roles.contains(r)) {
                it.remove();
                updated = true;
            }
        }

        // Ordre
        if (existing.getOrdre() != def.ordre) {
            existing.setOrdre(def.ordre);
            updated = true;
        }

        if (updated) {
            repository.save(existing);
            System.out.println("🔁 Menu mis à jour : " + def.name());
        } else {
            System.out.println("✅ Menu déjà à jour : " + def.name());
        }
    }


    private void createNewMenuItem(String key, MenuDefinition def) {
        MenuItem item = MenuItem.builder()
                .key(key)
                .label(def.label)
                .visible(true)
                .roles(new ArrayList<>(def.roles))
                .comptes(new ArrayList<>())
                .ordre(def.ordre)
                .build();
        repository.save(item);
        System.out.println("➕ Menu créé : " + key);
    }
}
