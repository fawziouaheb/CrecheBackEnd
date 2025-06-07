package projet.creche.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import projet.creche.configs.enums.RoleType;

@Getter
@Setter
@Schema(description = "Requête pour récupérer le menu visible d'un utilisateur")
public class MenuRequestDto {
    @Schema(description = "Rôle unique de l'utilisateur", example = "ADMIN", required = true)
    private String role;
}
