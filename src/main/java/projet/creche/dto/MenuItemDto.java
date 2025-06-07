package projet.creche.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItemDto {
    private String name;
    private String url;
    private Integer ordre;
}
