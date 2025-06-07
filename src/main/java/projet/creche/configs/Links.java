package projet.creche.configs;

public enum Links {


    LINKCONNEXION("http://localhost:4200/login"),
    LINKREPOSITORYEMPLOYEE("http://localhost:4200/monespace/admin/employee-details/"),
    LINKREPOSITORYPARENT("http://localhost:4200/monespace/admin/parent-details/"),
    LINKREUNION("http://localhost:4200/monespace/admin/rapport-reunion");


    private final String description;

    // Constructor
    Links(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
