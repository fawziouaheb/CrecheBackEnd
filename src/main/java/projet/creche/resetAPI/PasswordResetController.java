package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.service.PasswordResetService;
import projet.creche.tools.ApiResponse;

import java.util.Map;

@RestController
@RequestMapping("/reset-password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> requestReset(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, "Email requis"));
        }

        passwordResetService.envoyerLienReinitialisation(email);
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Lien de réinitialisation envoyé (si l'email existe)"));
    }


    @PostMapping("/confirm")
    public ResponseEntity<ApiResponse> confirmReset(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String newPassword = body.get("newPassword");

        if (token == null || newPassword == null || token.isBlank() || newPassword.isBlank()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, null, "Token et nouveau mot de passe requis")
            );
        }

        boolean success = passwordResetService.reinitialiserMotDePasse(token, newPassword);
        if (!success) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, null, "Token invalide ou expiré")
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, null, "Mot de passe réinitialisé avec succès")
        );
    }

    @GetMapping("/validate-reset-token")
    public ResponseEntity<ApiResponse> validateResetToken(@RequestParam String token) {
        boolean isValid = passwordResetService.validateResetToken(token);
        if (!isValid) {
            return ResponseEntity.ok(new ApiResponse(false,null, "Token invalide ou expiré"));
        }
        return ResponseEntity.ok(new ApiResponse(true, null, "Token valide"));
    }
}
