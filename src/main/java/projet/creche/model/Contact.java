package projet.creche.model;

import jakarta.persistence.*;

import java.sql.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity( name = "Contact")
@Table( name = "contact")
public class Contact {

    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "contact_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column ( name ="first_name", nullable = false, columnDefinition = "VARCHAR(40)")
    public String firstName;

    @Column ( name = "last_name", nullable = false, columnDefinition = "VARCHAR(40)")
    public String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100)")
    public String email;

    @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(10)")
    public String mobile;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    public String message;

    public Contact(){}

    public Contact(String firstName, String lastName, String email, String mobile, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
