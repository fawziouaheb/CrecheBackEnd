package projet.creche.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class CurrentUserDto {
    private Long id;
    private String username;
    private RoleDto role;
}


