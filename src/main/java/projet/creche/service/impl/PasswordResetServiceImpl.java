package projet.creche.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.creche.model.Compte;
import projet.creche.model.PasswordResetToken;
import projet.creche.repository.CompteRepository;
import projet.creche.repository.PasswordResetTokenRepository;
import projet.creche.service.PasswordResetService;
import projet.creche.tools.MailService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void envoyerLienReinitialisation(String email) {
        Optional<Compte> compteOpt = compteRepository.findByUsername(email);
        if (compteOpt.isEmpty()) return;

        Compte compte = compteOpt.get();

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setCompte(compte);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(2)); // expire dans 2h

        tokenRepository.save(resetToken);

        String lien = "http://localhost:4200/reset-password?token=" + token;

        mailService.envoyerLienReinitialisation(email, lien);
    }

    @Override
    @Transactional
    public boolean reinitialiserMotDePasse(String token, String nouveauMotDePasse) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);
        if (resetTokenOpt.isEmpty()) return false;

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(resetToken);
            return false;
        }

        Compte compte = resetToken.getCompte();
        compte.setPassword(passwordEncoder.encode(nouveauMotDePasse));
        compteRepository.save(compte);
        tokenRepository.delete(resetToken);
        return true;
    }

    @Override
    public boolean validateResetToken(String token) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByToken(token);
        if (resetTokenOpt.isEmpty()) {
            return false; // token inexistant
        }

        PasswordResetToken resetToken = resetTokenOpt.get();

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false; // token expiré
        }

        return true; // token valide
    }

}
