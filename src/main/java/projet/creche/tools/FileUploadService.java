package projet.creche.tools;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import projet.creche.configs.Links;
import projet.creche.configs.PathFile;
import projet.creche.utilitaires.PdfUploadRequest;

/**
 * Cette class c'est pour des methode pour tout ce qui upload d'un fichier
 * @author Fawzi Ouaheb
 */
public class FileUploadService {
    /**
     * cette méthode permet de charger un fichier et le sauvegarder en tant que pdf
     * @param request les données du fichier
     * @return boolean si l'opération à réussi ou echoue
     */
    public static Map<String, Object> uploadPdf(PdfUploadRequest request,String pathRepository) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(request.getFileData());
            Path uploadDir = Paths.get(pathRepository+ request.getIdPackage());
            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(request.getFileName());
            Files.write(filePath, decodedBytes);


            return Map.of("success", true, "message", "Fichier enregistré avec succès.","filePath", filePath.toAbsolutePath().toString());
        } catch (Exception e) {
            return Map.of("success", false, "message", "Erreur lors de l'enregistrement du fichier.");
        }
    }

    /**
     * cette méthode télécharger un fichier
     */
    public static String downloadPdf(String pathFile) throws Exception {
        Path filePath = Paths.get(pathFile);

        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Fichier non trouvé");
        }
        byte[] fileContent = Files.readAllBytes(filePath);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    /**
     * Cette méthode permet de supprimer un fichier PDF du dossier du parent.
     *
     * @param parentId L'ID du parent
     * @param fileName Le nom du fichier à supprimer
     * @return Une map indiquant le succès ou l'échec de l'opération
     */
    public static Map<String, Object> deletePdf(Long parentId, String fileName, String pathRepository) {
        try {
            Path filePath = Paths.get(pathRepository + parentId + "/"+fileName);
            if (!Files.exists(filePath)) {
                return Map.of("success", false, "message", "Fichier non trouvé !");
            }
            Files.delete(filePath);
            return Map.of("success", true, "message", "Fichier supprimé avec succès.");
        } catch (Exception e) {
            return Map.of("success", false, "message", "Erreur lors de la suppression du fichier.");
        }
    }

}


