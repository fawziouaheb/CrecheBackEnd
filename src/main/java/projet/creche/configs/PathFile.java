package projet.creche.configs;

public enum PathFile {

    CV("uploads/cv/"),
    PLANNING("uploads/planning/"),
    MENU("uploads/menu/"),
    REPOSITIRY_EMPLOYEE("uploads/dossier/employées/"),
    REPOSITORY_PARENT("uploads/dossier/parents/"),
    STRUCTURE("uploads/images/structure/");


    private final String description;

    // Constructor
    PathFile(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
