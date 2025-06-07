package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.ParentFeedback;

public interface ParentFeedbackRepository extends JpaRepository<ParentFeedback, Long> {
}

