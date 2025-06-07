package projet.creche.configs.jwtSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import projet.creche.model.Compte;
import projet.creche.model.Role;
import projet.creche.repository.CompteRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CompteRepository compteRepository;

    public CustomUserDetailsService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public UserDetails  loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Compte> compte = compteRepository.findByUsername(email);

        if (compte.isEmpty()) {
            throw new UsernameNotFoundException("Compte non trouvé avec l'email : " + email);
        }

        Role role = compte.get().getRole();
        if (role == null) {
            throw new UsernameNotFoundException("Le compte n'a pas de rôle assigné.");
        }

        // Créer un UserDetails à partir du Compte et de ses rôles
        return User.builder()
                .username(compte.get().getUsername())
                .password(compte.get().getPassword())
                .roles(role.getRoleName().name())
                .build();
    }
}
