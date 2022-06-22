package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_grupo")
public class Grupo {
    @Id
    @Column(name = "g_id", nullable = false)
    private Long g_id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_grupo_actor", joinColumns = @JoinColumn(name = "g_id", referencedColumnName = "g_id"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    List<Actor> g_actores;

    public Grupo() {
    }

    public Grupo(Long g_id, List<Actor> g_actores) {
        this.g_id = g_id;
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
