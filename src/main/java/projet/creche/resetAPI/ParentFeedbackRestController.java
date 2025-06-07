package projet.creche.resetAPI;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.model.ParentFeedback;
import projet.creche.service.ParentFeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/parent-feedback")
@RequiredArgsConstructor
public class ParentFeedbackRestController {

    @Autowired
    private ParentFeedbackService service;

    /**
     * Récupère tous les avis des parents
     */
    @GetMapping
    public ResponseEntity<List<ParentFeedback>> getAllFeedback() {
        List<ParentFeedback> feedbackList = service.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    /**
     * Enregistre un nouvel avis de parent
     */
    @PostMapping
    public ResponseEntity<ParentFeedback> createFeedback(@RequestBody ParentFeedback feedback) {
        try {
            ParentFeedback saved = service.saveFeedback(feedback);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // ou renvoyer un message d'erreur personnalisé
        }
    }
}
