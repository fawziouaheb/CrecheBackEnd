package projet.creche.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projet.creche.model.ParentFeedback;
import projet.creche.repository.ParentFeedbackRepository;

import java.util.List;

public interface ParentFeedbackService {

    public List<ParentFeedback> getAllFeedback();

    public ParentFeedback addFeedback(ParentFeedback feedback);

    public ParentFeedback saveFeedback(ParentFeedback feedback);
}
