package projet.creche.resetAPI;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import projet.creche.dto.*;
import projet.creche.mapper.CompteMapper;
import projet.creche.mapper.RoleMapper;
import projet.creche.model.Compte;
import projet.creche.model.Role;
import projet.creche.repository.CompteRepository;
import projet.creche.configs.jwtSecurity.JwtService;
import projet.creche.configs.jwtSecurity.CustomUserDetailsService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private CompteRepository compteRepository;

    public AuthRestController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService, CompteRepository compteRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.compteRepository = compteRepository;
    }

    // 📌 Connexion : Génère un token JWT après authentification
    @Operation(summary = "Connexion et génération du token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        System.out.println("Tentative de connexion pour : " + request.getUsername());
        Optional<Compte> compte = compteRepository.findByUsername(request.getUsername());

        if (compte.isPresent()) {
            System.out.println("Utilisateur trouvé en base : " + compte.get().getUsername());
        } else {
            System.out.println("Utilisateur introuvable en base !");
        }

        try {
            // Authentifier l'utilisateur
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Récupérer l'utilisateur après authentification via CustomUserDetailsService
            UserDetails compteDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

            String token = jwtService.generateToken(compteDetails);
            String refreshToken = jwtService.generateRefreshToken(compteDetails);

            return ResponseEntity.ok(new AuthResponseDto(token, refreshToken));
        } catch (BadCredentialsException e) {
            // Gestion de l'erreur avec uniquement le message
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthResponseDto("Identifiants invalides"));
        }
    }


    @Operation(summary = "Récupérer les infos du compte connecté")
    @GetMapping("/me")
    public ResponseEntity<CurrentUserDto> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtService.extractUsername(token.substring(7));
            Compte compte = compteRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            RoleDto role = RoleMapper.mappeEntityToDto(compte.getRole());

            CurrentUserDto currentUserDto = new CurrentUserDto();
            currentUserDto.setId(compte.getId());
            currentUserDto.setUsername(compte.getUsername());
            currentUserDto.setRole(role);

            return ResponseEntity.ok(currentUserDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 📌 Rafraîchir le token JWT
    @Operation(summary = "Rafraîchir le token JWT")
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestBody AuthResponseDto request) {
        String newToken = jwtService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(new AuthResponseDto(newToken, request.getRefreshToken()));
    }
}
