package com.modeloanalitica.uahdatos.modelo;

import javax.persistence.*;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @Column(name = "r_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long r_id;

    @Column(name = "r_rol")
    private String r_rol;

    public Role() {

    }

    public Role(Long r_id, String r_rol) {
        this.r_id = r_id;
        this.r_rol = r_rol;
    }

    public Long getR_id() {
        return r_id;
    }

    public void setR_id(Long r_id) {
        this.r_id = r_id;
    }

    public String getR_rol() {
        return r_rol;
    }

    public void setR_rol(String r_rol) {
        this.r_rol = r_rol;
    }
}
