package projet.creche.dto;

public class ActiviteDto {

    private Long id;              // Identifiant unique de l'activité
    private String name;          // Nom de l'activité
    private String description;   // Description de l'activité
    private String imageUrl;      // URL de l'image de l'activité

    // Constructeur vide
    public ActiviteDto() {}

    // Constructeur avec paramètres
    public ActiviteDto(Long id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
