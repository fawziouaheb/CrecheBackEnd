package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);
}
