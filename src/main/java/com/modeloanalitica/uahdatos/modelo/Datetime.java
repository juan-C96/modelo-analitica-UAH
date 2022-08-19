package com.modeloanalitica.uahdatos.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_datetime")
public class Datetime {
    @Id
    @Column(name = "date_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long date_id;

    @Column(name = "date_fecha")
    private String date_fecha;

    @Column(name = "date_personasString")
    String date_personasString;

    @Column(name = "date_personas")
    @ElementCollection
    List<String> date_personas;

    @ManyToMany
    @JoinTable(name = "date_personas_conectadas", joinColumns = @JoinColumn(name = "date_id", referencedColumnName = "date_id"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    List<Actor> date_personas_conectadas;

    public Datetime() {
    }

    public Datetime(Long date_id, String date_fecha, String date_personasString, List<String> date_personas, List<Actor> date_personas_conectadas) {
        this.date_id = date_id;
        this.date_fecha = date_fecha;
        this.date_personasString = date_personasString;
        this.date_personas = date_personas;
        this.date_personas_conectadas = date_personas_conectadas;
    }

    public Long getDate_id() {
        return date_id;
    }

    public void setDate_id(Long date_id) {
        this.date_id = date_id;
    }

    public String getDate_fecha() {
        return date_fecha;
    }

    public void setDate_fecha(String date_fecha) {
        this.date_fecha = date_fecha;
    }

    public List<String> getDate_personas() {
        return date_personas;
    }

    public void setDate_personas(List<String> date_personas) {
        this.date_personas = date_personas;
    }

    public List<Actor> getDate_personas_conectadas() {
        return date_personas_conectadas;
    }

    public void setDate_personas_conectadas(List<Actor> date_personas_conectadas) {
        this.date_personas_conectadas = date_personas_conectadas;
    }

    public String getDate_personasString() {
        return date_personasString;
    }

    public void setDate_personasString(String date_personasString) {
        this.date_personasString = date_personasString;
    }
}
