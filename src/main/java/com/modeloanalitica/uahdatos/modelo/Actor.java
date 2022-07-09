package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_actor")
public class Actor {
    @Id
    @Column(name = "a_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long a_id;

    @Column(name = "a_id_real", nullable = false)
    private String a_id_real;

    @Column(name = "a_usuario")
    private String a_usuario;

    @Column(name = "a_tipo")
    private String a_tipo;

    @OneToMany
    @JoinTable(name = "t_actor_role", joinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"), inverseJoinColumns = @JoinColumn(name = "r_id", referencedColumnName = "r_id"))
    List<Role> a_rol;

    public Actor() {
    }

    public Actor(String a_id_real, String a_tipo) {
        this.a_id_real = a_id_real;
        this.a_tipo = a_tipo;
    }

    public Actor(Long a_id, String a_id_real, String a_usuario, String a_tipo, List<Role> a_rol) {
        this.a_id = a_id;
        this.a_id_real = a_id_real;
        this.a_usuario = a_usuario;
        this.a_tipo = a_tipo;
        this.a_rol = a_rol;
    }

    public Long getA_id() {
        return a_id;
    }

    public void setA_id(Long a_id) {
        this.a_id = a_id;
    }

    public List<Role> getA_rol() {
        return a_rol;
    }

    public void setA_rol(List<Role> a_rol) {
        this.a_rol = a_rol;
    }

    public String getA_id_real() {
        return a_id_real;
    }

    public void setA_id_real(String a_id_real) {
        this.a_id_real = a_id_real;
    }

    public String getA_usuario() {
        return a_usuario;
    }

    public void setA_usuario(String a_usuario) {
        this.a_usuario = a_usuario;
    }

    public String getA_tipo() {
        return a_tipo;
    }

    public void setA_tipo(String a_tipo) {
        this.a_tipo = a_tipo;
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
