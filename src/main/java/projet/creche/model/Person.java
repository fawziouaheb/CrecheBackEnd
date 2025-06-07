package projet.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.List;
import java.util.Objects;


/**
 * Classe représentant les informations communes à toutes les personnes.
 * @author Fawzi Ouaheb
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long id;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "date_birth", nullable = false)
    private Date dateBirth;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_id")
    private Structure structure;

    /**
     * Relation bidirectionnelle avec Compte.
     * La gestion de la persistance est contrôlée côté Compte (cascade sur Compte).
     */
    @JsonIgnore
    @OneToOne(mappedBy = "person")
    private Compte compte;

    @OneToMany(
            mappedBy = "person",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<File> files;

    @OneToMany(
            mappedBy = "person",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Menu> menus;

    /** Constructeurs */
    public Person() {}

    public Person(String firstName, String lastName, Date dateBirth, String email, String mobile,
                  Structure structure, Compte compte) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.email = email;
        this.mobile = mobile;
        this.structure = structure;
        this.compte = compte;
    }

    /** Getters & Setters */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getDateBirth() { return dateBirth; }
    public void setDateBirth(Date dateBirth) { this.dateBirth = dateBirth; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public Structure getStructure() { return structure; }
    public void setStructure(Structure structure) { this.structure = structure; }


    public Compte getCompte() { return compte; }
    public void setCompte(Compte compte) { this.compte = compte; }

    public List<File> getFiles() { return files; }
    public void setFiles(List<File> files) { this.files = files; }

    /** Equals & HashCode */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    /** toString simplifié */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + firstName + " " + lastName + '\'' +
                '}';
    }
}
