package projet.creche.service;

import projet.creche.model.Candidate;

import java.util.List;

public interface CandidateService {
    public Candidate addCandidate(Candidate candidate);
    List<Candidate> getAll();
    Candidate  findByEmail(String email);
    boolean isValidAndFilter(Candidate candidate);
    Candidate findById(Long id);
    void delete(Candidate candidate);

}
