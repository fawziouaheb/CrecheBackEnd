package projet.creche.model;

import jakarta.persistence.*;

@Entity
@Table(name = "protocoles")
public class Protocole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    public Protocole() {
    }
    public Protocole(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
}