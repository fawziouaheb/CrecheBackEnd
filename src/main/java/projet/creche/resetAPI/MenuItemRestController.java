package projet.creche.resetAPI;

import org.springframework.web.bind.annotation.*;
import projet.creche.configs.enums.RoleType;
import projet.creche.dto.MenuItemDto;
import projet.creche.dto.MenuRequestDto;
import projet.creche.service.MenuItemService;
import projet.creche.service.RoleService;
import projet.creche.tools.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemRestController {

    private final MenuItemService service;

    public MenuItemRestController(MenuItemService service) {
        this.service = service;
    }

    @PostMapping("/getMenu")
    public ApiResponse<List<MenuItemDto>> getMenu(@RequestBody MenuRequestDto menuRequestDto) {
        RoleType role = RoleType.valueOf(menuRequestDto.getRole());
        List<MenuItemDto> menu = service.getMenuForRole(role);
        return new ApiResponse<>(true, menu, "Menu récupéré avec succès");
    }

}
