package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_grupo")
public class Grupo {
    @Id
    @Column(name = "g_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long g_id;

    @Column(name = "g_id_real", nullable = false)
    private String g_id_real;

    @Column(name = "g_tipo", nullable = false)
    private String g_tipo;

    @Column(name = "g_numero", nullable = false)
    private String g_numero;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_grupo_actor", joinColumns = @JoinColumn(name = "g_id", referencedColumnName = "g_id"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    List<Actor> g_actores;

    public Grupo() {
    }

    public Grupo(String g_id_real, String g_tipo, String g_numero) {
        this.g_id_real = g_id_real;
        this.g_tipo = g_tipo;
        this.g_numero = g_numero;
    }

    public Grupo(Long g_id, String g_id_real, String g_tipo, String g_numero, List<Actor> g_actores) {
        this.g_id = g_id;
        this.g_id_real = g_id_real;
        this.g_tipo = g_tipo;
        this.g_numero = g_numero;
        this.g_actores = g_actores;
    }

    public Long getG_id() {
        return g_id;
    }

    public void setG_id(Long g_id) {
        this.g_id = g_id;
    }

    public List<Actor> getG_actores() {
        return g_actores;
    }

    public void setG_actores(List<Actor> g_actores) {
        this.g_actores = g_actores;
    }

    public String getG_id_real() {
        return g_id_real;
    }

    public void setG_id_real(String g_id_real) {
        this.g_id_real = g_id_real;
    }

    public String getG_tipo() {
        return g_tipo;
    }

    public void setG_tipo(String g_tipo) {
        this.g_tipo = g_tipo;
    }

    public String getG_numero() {
        return g_numero;
    }

    public void setG_numero(String g_numero) {
        this.g_numero = g_numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(g_id, grupo.g_id) && Objects.equals(g_actores, grupo.g_actores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(g_id, g_actores);
    }
}
