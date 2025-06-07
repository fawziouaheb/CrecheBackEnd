package projet.creche.dto;

public class ProtocoleDto {

    private Long id;              // Identifiant unique du protocole
    private String titre;         // Titre du protocole
    private String contenu;       // Contenu du protocole

    // Constructeur vide
    public ProtocoleDto() {}

    // Constructeur avec paramètres
    public ProtocoleDto(Long id, String titre, String contenu) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
