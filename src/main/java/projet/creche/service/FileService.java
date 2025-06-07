package projet.creche.service;

import projet.creche.model.File;

public interface FileService {

    File persistFile(File file);
    void deleteByName(String fileName);
}
