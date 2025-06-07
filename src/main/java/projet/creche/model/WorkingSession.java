package projet.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Time;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity( name = "WorkingSession")
@Table ( name = "workingsession")
public class WorkingSession {
    @Id
    @SequenceGenerator(
            name = "working_session_sequence",
            sequenceName = "working_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "working_session_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name ="date_session", nullable = false,columnDefinition = "DATE")
    private Date dateSession;


    @Column(name="arrival_time",nullable = false,columnDefinition = "TIME")
    private Time arrivalTime;

    @Column(name = "departure_time", nullable = false, columnDefinition = "TIME")
    private Time departureTime;

    @Column(name ="duration_minute", nullable= false)
    private Integer durationMinute;

    @Column(name = "type_enregister", nullable = false)
    private String typeEnregistration;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    public Date createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSession() {
        return dateSession;
    }

    public void setDateSession(Date dateSession) {
        this.dateSession = dateSession;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(Integer durationMinute) {
        this.durationMinute = durationMinute;
    }

    public String getTypeEnregistration() {
        return typeEnregistration;
    }

    public void setTypeEnregistration(String typeEnregistration) {
        this.typeEnregistration = typeEnregistration;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
