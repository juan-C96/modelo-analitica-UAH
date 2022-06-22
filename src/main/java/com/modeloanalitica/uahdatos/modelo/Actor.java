package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "t_actor")
public class Actor {
    @Id
    @Column(name = "a_id", nullable = false)
    private Long a_id;

    @Column(name = "a_rol")
    private String a_rol;

    public Actor() {
    }

    public Actor(Long a_id, String a_rol) {
        this.a_id = a_id;
        this.a_rol = a_rol;
    }

    public Long getA_id() {
        return a_id;
    }

    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public String getA_rol() {
        return a_rol;
    }

    public void setA_rol(String a_rol) {
        this.a_rol = a_rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(a_id, actor.a_id) && Objects.equals(a_rol, actor.a_rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a_id, a_rol);
    }
}
