package projet.creche.service;

import projet.creche.configs.enums.RoleType;
import projet.creche.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    public List<MenuItemDto> getMenuForRole(RoleType role);
}
