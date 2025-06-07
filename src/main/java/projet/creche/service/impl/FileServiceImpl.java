package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.File;
import projet.creche.model.Person;
import projet.creche.repository.FileRepository;
import projet.creche.repository.PersonRepository;
import projet.creche.service.FileService;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;
    private PersonRepository personRepository;
    @Autowired
    public FileServiceImpl(FileRepository fileRepository, PersonRepository personRepository) {
        this.fileRepository = fileRepository;
        this.personRepository = personRepository;
    }

    @Override
    public File persistFile(File file) {
        return this.fileRepository.save(file);
    }

    @Transactional
    @Override
    public void deleteByName(String fileName) {
        try {
            File file = fileRepository.findByFileName(fileName);

            if (file != null) {
                Person person = file.getPerson();  // Récupérer la personne associée au fichier
                if (person != null) {
                    person.getFiles().remove(file);
                    personRepository.save(person);
                }
                fileRepository.delete(file);
                System.out.println("Fichier supprimé de la base de données.");
            } else {
                System.out.println("Aucun fichier trouvé avec ce nom.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            e.printStackTrace();
        }
    }




}
