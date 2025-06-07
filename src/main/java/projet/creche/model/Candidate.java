    package projet.creche.model;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import org.hibernate.annotations.CollectionId;
    import org.hibernate.annotations.CreationTimestamp;

    import java.sql.Date;
    import java.util.HashSet;
    import java.util.Set;

    import static jakarta.persistence.GenerationType.SEQUENCE;


    /**
     * cette entité contient les informations d'un candidat
     * @author Fawzi Ouaheb
     */
    @Entity(name = "Candidate")
    @Table(name = "candidate")
    public class Candidate {

        @Id
        @SequenceGenerator(
                name = "role_sequence",
                sequenceName = "role_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "role_sequence"
        )
        private Long id;

        @Column(
                name = "firstName",
                nullable = false,
                columnDefinition = "VARCHAR(30)"
        )
        private String firstName;

        @Column(
                name = "lastName",
                nullable = false,
                columnDefinition = "VARCHAR(30)"
        )
        private String lastName;

        @Column(
                name = "email",
                nullable = false,
                columnDefinition = "VARCHAR(70)"
        )
        private String email;

        @Column(
                name = "mobile",
                nullable = false,
                columnDefinition = "VARCHAR(10)"
        )
        private String mobile;

        @Column(
                name = "statut",
                nullable = false,
                columnDefinition = "VARCHAR(20)"
        )

        private String statut;
        @Column(name = "created_at", nullable = false, updatable = false)
        @CreationTimestamp
        private Date createdAt;

        @Column(
                name = "cv",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String cv;

        @Column (
                name = "motivation",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String motivation;
        @Column (
                name = "contract",
                nullable = false,
                columnDefinition = "VARCHAR(20)"
        )
        private String contract;
        @Column (
                name = "city",
                nullable = false,
                columnDefinition = "VARCHAR(30)"
        )
        private String city;

        @Column (
                name = "date_free",
                nullable = true,
                columnDefinition = "DATE"
        )
        private  Date dateFree;

        @ManyToMany(mappedBy = "candidates", cascade = {CascadeType.MERGE})
        private Set<Structure> structures = new HashSet<>();


        public Candidate() {
            // Constructeur par défaut
        }

        public Candidate(String firstName, String lastName, String email, String mobile, String statut, String cv, String motivation, Set<Structure> structures,String city, String contract, Date dateFree) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.mobile = mobile;
            this.statut = statut;
            this.cv = cv;
            this.motivation = motivation;
            this.structures = structures != null ? structures : new HashSet<>(); // Assure-toi que la liste n'est pas nulle
            this.city = city;
            this.contract = contract;
            this.dateFree  = dateFree;
        }

        public Set<Structure> getStructures() {
            return structures;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
        }


        public String getMotivation() {
            return motivation;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public String getcv() {
            return cv;
        }

        public Date getDateFree() {
            return dateFree;
        }

        public void setDateFree(Date dateFree) {
            this.dateFree = dateFree;
        }
        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public String getMobile() {
            return mobile;
        }


        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String toString() {
            return "Candidate{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", statut='" + statut + '\'' +
                    ", createdAt=" + createdAt +
                    ", cv='" + cv + '\'' +
                    ", motivation='" + motivation + '\'' +
                    ", structures=" + structures.size() + " structures" + // Nombre de structures associées
                    '}';
        }

    }
