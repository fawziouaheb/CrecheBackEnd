package projet.creche.service;

import projet.creche.model.Compte;
import projet.creche.model.Parent;

public interface CompteService {
    Compte findById(Long compteId);
    Compte save(Compte compte);
    Compte generateCompte();
    Compte createCompteParent(Parent parent);
}
