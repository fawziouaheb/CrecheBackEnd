package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.File;


public interface FileRepository extends JpaRepository<File, Long> {
    void deleteByFileName(String fileName);
    long countByFileName(String fileName);
    File findByFileName(String fileName);
}
