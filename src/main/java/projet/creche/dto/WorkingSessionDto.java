package projet.creche.dto;

import projet.creche.model.Employe;

import java.sql.Date;
import java.sql.Time;

public class WorkingSessionDto  {

    private Long id;
    private Date dateSession;
    private Time arrivalTime;
    private Time departureTime;
    private Integer durationMinute;
    private String typeEnregistration;
    private Long employeId;
    private Employe employe;
    // Getters et Setters

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

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
