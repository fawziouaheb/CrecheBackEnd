package projet.creche.service.impl;

import org.springframework.stereotype.Service;
import projet.creche.configs.enums.MenuDefinition;
import projet.creche.configs.enums.RoleType;
import projet.creche.dto.MenuItemDto;
import projet.creche.model.MenuItem;
import projet.creche.repository.MenuItemRepository;
import projet.creche.service.MenuItemService;

import java.util.Comparator;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemServiceImpl(MenuItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MenuItemDto> getMenuForRole(RoleType role) {
        return repository.findByVisibleTrueAndRolesContaining(role).stream()
                .sorted(Comparator.comparingInt(MenuItem::getOrdre))
                .map(item -> {
                    MenuDefinition def = MenuDefinition.valueOf(item.getKey());
                    return new MenuItemDto(
                            def.label,
                            def.urls.getOrDefault(role, ""),
                            def.ordre
                    );
                })
                .toList();
    }
}
