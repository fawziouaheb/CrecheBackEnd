package projet.creche.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * cette class représente les informations d'un compte sur le système d'information
 */
@Entity( name = "City")
@Table( name = "city")
public class    City {
    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "city_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name= "name", nullable = false, columnDefinition = "VARCHAR(30)")
    private String name;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(
            mappedBy = "city",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private Set<Structure> structures = new HashSet<>();
    public City() {}

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

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", structures=" + structures +
                '}';
    }
}
