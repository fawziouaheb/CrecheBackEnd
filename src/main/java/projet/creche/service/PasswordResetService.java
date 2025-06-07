package projet.creche.service;

public interface PasswordResetService {
    void envoyerLienReinitialisation(String email);
    boolean reinitialiserMotDePasse(String token, String newPassword);
    boolean validateResetToken(String token);
}