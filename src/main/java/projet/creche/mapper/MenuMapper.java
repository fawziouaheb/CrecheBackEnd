package projet.creche.mapper;

import projet.creche.dto.MenuDto;
import projet.creche.model.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {

    // Convertir Menu en MenuDTO
    public static MenuDto toMenuDTO(Menu menu) {
        if (menu == null) {
            return null;
        }
        return new MenuDto(
                menu.getId(),
                menu.getTitle(),
                menu.getCreatedAt(),
                menu.getPerson() != null ? menu.getPerson().getId() : null
        );
    }

    // Convertir MenuDTO en Menu
    public static Menu toMenu(MenuDto menuDTO) {
        if (menuDTO == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        menu.setTitle(menuDTO.getTitle());
        menu.setCreatedAt(menuDTO.getCreatedAt());

        // Pour l'exemple, on pourrait créer une référence Person ici si nécessaire
        // menu.setPerson(person);

        return menu;
    }

    // Convertir une liste de Menu en une liste de MenuDTO
    public static List<MenuDto> toMenuDTOList(List<Menu> menus) {
        return menus.stream()
                .map(MenuMapper::toMenuDTO)
                .collect(Collectors.toList());
    }

    // Convertir une liste de MenuDTO en une liste de Menu
    public static List<Menu> toMenuList(List<MenuDto> menuDTOs) {
        return menuDTOs.stream()
                .map(MenuMapper::toMenu)
                .collect(Collectors.toList());
    }
}
