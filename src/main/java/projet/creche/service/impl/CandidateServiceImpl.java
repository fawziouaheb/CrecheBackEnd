package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.Candidate;
import projet.creche.model.Structure;
import projet.creche.repository.CandidateRepository;
import projet.creche.service.CandidateService;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return this.candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAll() {
        return this.candidateRepository.findAll();
    }

    @Override
    public Candidate findByEmail(String email) {
        return this.candidateRepository.findByEmail(email);
    }

    /**
     * This method validates and formats the data of a Candidate model.
     * @param candidate the candidate to validate and format
     * @return true if the candidate data is valid, false otherwise
     */
    public boolean isValidAndFilter(Candidate candidate) {
        if (candidate == null) {
            return false;
        }

        // Retrieve candidate fields
        String firstName = candidate.getFirstName();
        String lastName = candidate.getLastName();
        String email = candidate.getEmail();
        String mobile = candidate.getMobile();
        String statut = candidate.getStatut();
        String motivation = candidate.getMotivation();
        String city = candidate.getCity();

        // Check for empty fields and format if needed
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                mobile == null || mobile.trim().isEmpty() ||
                statut == null || statut.trim().isEmpty() ||
                motivation == null || motivation.trim().isEmpty() ||
                city == null || city.trim().isEmpty() ||
                mobile.length() != 10) { // Ensure mobile number is exactly 10 digits
            return false;
        }

        // Trim and format important fields
        firstName = firstName.trim().toUpperCase();
        lastName = lastName.trim().toUpperCase();
        email = email.trim().toUpperCase();
        mobile = mobile.trim();
        statut = statut.trim();
        city = city.trim().toUpperCase();

        // Set formatted values back to the candidate
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setEmail(email);
        candidate.setMobile(mobile);
        candidate.setCity(city);
        return true; // Return true if all validations pass
    }

    @Override
    public Candidate findById(Long id) {
        return this.candidateRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Candidate candidate) {
        for (Structure structure : candidate.getStructures()) {
            structure.getCandidates().remove(candidate);
        }
        candidate.getStructures().clear();
        this.candidateRepository.delete(candidate);

    }

}
