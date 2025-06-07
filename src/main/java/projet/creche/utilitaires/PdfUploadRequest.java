package projet.creche.utilitaires;

/**
 * Cette class c'est utilisé pour présenter la structure d'un fichier
 * @author Fawzi Ouaheb
 */
public class PdfUploadRequest {

    private Long idPackage;
    private String fileName;
    private String fileData; // Encodé en Base64

    // Getters et Setters
    public Long getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(Long idPackage) {
        this.idPackage = idPackage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "PdfUploadRequest{" +
                "idPackage=" + idPackage +
                ", fileName='" + fileName + '\'' +
                ", fileData='" + fileData + '\'' +
                '}';
    }
}
