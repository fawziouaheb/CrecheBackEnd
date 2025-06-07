package projet.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import projet.creche.configs.enums.RoleType;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

/**
 * Cette classe représente les informations d'un compte utilisateur du système d'information.
 */
@Entity
@Table(name = "compte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "compte_sequence",
            sequenceName = "compte_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "compte_sequence"
    )
    private Long id;

    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "last_connexion")
    private Date lastConnexion;

    @Column(name = "password_changed", nullable = false)
    private boolean passwordChanged;

    /**
     * La personne associée au compte.
     * La persistance de Person est automatisée via CascadeType.PERSIST.
     */
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    /**
     * Le rôle associé au compte.
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    private Role role;

    /**
     * Retourne l'autorité (rôle) de l'utilisateur pour Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        RoleType roleType = role.getRoleName();
        return Collections.singletonList(new SimpleGrantedAuthority(roleType.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", lastConnexion=" + lastConnexion +
                ", passwordChanged=" + passwordChanged +
                ", person=" + (person != null ? person.getId() : "null") +
                ", role=" + (role != null ? role.getRoleName().name() : "null") +
                '}';
    }
}