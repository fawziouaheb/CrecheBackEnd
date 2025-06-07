package projet.creche.dto;

import java.sql.Date;

public class MenuDto {

    private Long id;
    private String title;
    private String imageUrl; // URL de l'image du menu
    private Date createdAt;
    private Long personId; // Utilisateur qui a créé le menu (référence à Person)

    // Constructeurs
    public MenuDto() {
    }

    public MenuDto(Long id, String title, Date createdAt, Long personId) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.personId = personId;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
