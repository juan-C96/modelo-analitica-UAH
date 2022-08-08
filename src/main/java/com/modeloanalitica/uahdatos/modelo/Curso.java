package com.modeloanalitica.uahdatos.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_curso", nullable = false)
    private Long id_curso;

    @Column(name = "c_id_real")
    private String c_id_real;

    @Column(name = "c_numero")
    private String c_numero;

    @Column(name = "c_numero_actividades")
    private int c_numero_actividades;

    @Column(name = "c_numero_actividades_terminadas")
    private int c_numero_actividades_terminadas;

    @Column(name = "c_fechaInicio")
    @DateTimeFormat(pattern = "YYYY-MM-DD:mm:ss")
    private LocalDateTime c_fechaInicio;

    @Column(name = "c_tiempo_horas")
    private int c_tiempo_horas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_curso_actor", joinColumns = @JoinColumn(name = "id_curso", referencedColumnName = "id_curso"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    List<Actor> c_actores;

    @Column(name = "c_personas")
    private String c_personas;

    public Curso() {
    }

    public Curso(Long id_curso, String c_id_real, String c_numero, int c_numero_actividades, int c_numero_actividades_terminadas, LocalDateTime c_fechaInicio, int c_tiempo_horas, List<Actor> c_actores, String c_personas) {
        this.id_curso = id_curso;
        this.c_id_real = c_id_real;
        this.c_numero = c_numero;
        this.c_numero_actividades = c_numero_actividades;
        this.c_numero_actividades_terminadas = c_numero_actividades_terminadas;
        this.c_fechaInicio = c_fechaInicio;
        this.c_tiempo_horas = c_tiempo_horas;
        this.c_actores = c_actores;
        this.c_personas = c_personas;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    public String getC_id_real() {
        return c_id_real;
    }

    public void setC_id_real(String c_id_real) {
        this.c_id_real = c_id_real;
    }

    public String getC_numero() {
        return c_numero;
    }

    public void setC_numero(String c_numero) {
        this.c_numero = c_numero;
    }

    public int getC_numero_actividades() {
        return c_numero_actividades;
    }

    public void setC_numero_actividades(int c_numero_actividades) {
        this.c_numero_actividades = c_numero_actividades;
    }

    public int getC_numero_actividades_terminadas() {
        return c_numero_actividades_terminadas;
    }

    public void setC_numero_actividades_terminadas(int c_numero_actividades_terminadas) {
        this.c_numero_actividades_terminadas = c_numero_actividades_terminadas;
    }

    public LocalDateTime getC_fechaInicio() {
        return c_fechaInicio;
    }

    public void setC_fechaInicio(LocalDateTime c_fechaInicio) {
        this.c_fechaInicio = c_fechaInicio;
    }

    public int getC_tiempo_horas() {
        return c_tiempo_horas;
    }

    public void setC_tiempo_horas(int c_tiempo_horas) {
        this.c_tiempo_horas = c_tiempo_horas;
    }

    public List<Actor> getC_actores() {
        return c_actores;
    }

    public void setC_actores(List<Actor> c_actores) {
        this.c_actores = c_actores;
    }

    public String getC_personas() {
        return c_personas;
    }

    public void setC_personas(String c_personas) {
        this.c_personas = c_personas;
    }
}
