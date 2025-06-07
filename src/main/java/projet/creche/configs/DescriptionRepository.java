package projet.creche.configs;

public enum DescriptionRepository {

    PARENT("Dossier des parents : Ce dossier contient des documents relatifs à l'inscription des enfants, ainsi que des informations financières"),
    DIRECTOR("Dossier de la directrice : Ce dossier contient des documents relatifs à l'état civil de la directrice, ainsi que des documents administratifs concernant son statut professionnel."),
    EMPLOYE(" Dossier des employés : Ce dossier contient des informations sur les membres du personnel, notamment les documents relatifs à leur état civil et leur rémunération.");

    private final String description;

    // Constructor
    DescriptionRepository(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
