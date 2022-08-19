package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actividad {
    @Id
    @Column(name = "activity_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long activity_id;

    @Column(name = "activity_name")
    private String activity_name;

    @Column(name = "activity_type")
    private String activity_type;


    @Column(name = "activity_personas")
    @ElementCollection
    List<String> activity_personas;

    @ManyToMany
    @JoinTable(name = "t_activity_actors_comleted", joinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "activity_id"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    List<Actor> activity_actors_comleted;


    public Actividad() {
    }

    public Actividad(Long activity_id, String activity_name, String activity_type, List<Actor> activity_actors_comleted, List<String> activity_personas) {
        this.activity_id = activity_id;
        this.activity_name = activity_name;
        this.activity_type = activity_type;
        this.activity_actors_comleted = activity_actors_comleted;
        this.activity_personas = activity_personas;
    }

    public List<String> getActivity_personas() {
        return activity_personas;
    }

    public void setActivity_personas(List<String> activity_personas) {
        this.activity_personas = activity_personas;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public List<Actor> getActivity_actors_comleted() {
        return activity_actors_comleted;
    }

    public void setActivity_actors_comleted(List<Actor> activity_actors_comleted) {
        this.activity_actors_comleted = activity_actors_comleted;
    }
}
