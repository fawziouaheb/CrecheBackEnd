package projet.creche.model;

import jakarta.persistence.*;
import lombok.*;
import projet.creche.configs.enums.RoleType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items") // nom clair et pluriel, pas un mot réservé
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_key", nullable = false, unique = true) // évite "key"
    private String key;

    @Column(nullable = false)
    private String label;

    @Column(name = "is_visible", nullable = false)
    private boolean visible = true;

    // Association aux rôles autorisés
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "menu_item_role_links", // nom explicite, évite collision
            joinColumns = @JoinColumn(name = "menu_item_id")
    )
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<RoleType> roles = new ArrayList<>();

    // Association aux comptes spécifiques
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "menu_item_compte_links", // nom explicite, évite mots-clés
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "compte_id")
    )
    private List<Compte> comptes = new ArrayList<>();

    @Column(name = "ordre")
    private Integer ordre;
}