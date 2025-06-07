package projet.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import projet.creche.configs.enums.RoleType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Modèle sécurisé représentant les rôles fixes de l'application.
 */
@Entity
@Table(name = "role")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // Pour JPA
@ToString(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 16, nullable = false, updatable = false)
    @ToString.Include
    private RoleType roleName;

    @Column(name = "role_description", nullable = false, columnDefinition = "TEXT")
    @ToString.Include
    private String roleDescription;

    @Column(name = "access_level", nullable = false)
    @ToString.Include
    private int accessLevel;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Compte> comptes = new HashSet<>();

    /**
     * Constructeur pour initialiser un rôle fixe.
     */
    public Role(RoleType roleName) {
        this.roleName = roleName;
        this.roleDescription = roleName.getDescription();
        this.accessLevel = roleName.getAccessLevel();
    }

    /**
     * Pas de setter public pour garantir l'intégrité des rôles.
     */
    private void setRoleName(RoleType roleName) {
        this.roleName = roleName;
        this.roleDescription = roleName.getDescription();
        this.accessLevel = roleName.getAccessLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
