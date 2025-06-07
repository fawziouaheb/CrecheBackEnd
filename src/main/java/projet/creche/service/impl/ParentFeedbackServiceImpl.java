package projet.creche.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projet.creche.model.ParentFeedback;
import projet.creche.repository.ParentFeedbackRepository;
import projet.creche.service.ActiviteService;
import projet.creche.service.ParentFeedbackService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ParentFeedbackServiceImpl implements ParentFeedbackService {

    private final ParentFeedbackRepository repository;

    public ParentFeedbackServiceImpl(ParentFeedbackRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ParentFeedback> getAllFeedback() {
        return repository.findAll();
    }

    @Override
    public ParentFeedback addFeedback(ParentFeedback feedback) {
        feedback.setDate(LocalDate.now());
        return repository.save(feedback);
    }

    public ParentFeedback saveFeedback(ParentFeedback feedback) {
        return repository.save(feedback);
    }
}
