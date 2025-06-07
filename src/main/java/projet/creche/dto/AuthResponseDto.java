package projet.creche.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {

    private String message;
    private String token;
    private String refreshToken;

    // Constructeur pour une erreur
    public AuthResponseDto(String message) {
        this.message = message;
    }

    // Constructeur pour une réponse réussie avec token
    public AuthResponseDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    // Getters et setters...
}


