package com.modeloanalitica.uahdatos.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "t_evento")
public class Evento {
    @Id
    @Column(name = "e_uuid", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long e_uuid;

    @Column(name = "e_uuid_real", nullable = false)
    private String e_uuid_real;

    @Column(name = "e_tipo")
    private String e_tipo;

    @Column(name = "e_edApp")
    private String e_edApp;

    @Column(name = "e_edApp_ip")
    private String e_edApp_ip;

    @Column(name = "e_accion")
    private String e_accion;

    @Column(name = "e_objeto")
    private String e_objeto;

    @Column(name = "e_objeto_type")
    private String e_objeto_type;

    @Column(name = "e_objeto_maxScore")
    private String e_objeto_maxScore;

    @Column(name = "e_objeto_maxAttempts")
    private String e_objeto_maxAttempts;

    @Column(name = "e_objeto_maxSubmits")
    private String e_objeto_maxSubmits;

    @Column(name = "e_target_generated")
    private String e_target_generated;

    @Column(name = "e_datetime")
    @DateTimeFormat(pattern = "YYYY-MM-DD:mm:ss")
    private LocalDateTime e_datetime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_evento_actor", joinColumns = @JoinColumn(name = "e_uuid", referencedColumnName = "e_uuid"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    Actor e_actor;

    @Column(name = "e_usuarios")
    private String e_usuarios;

    @Column(name = "e_grupos")
    private String e_grupos;

    public Evento() {
    }

    public Evento(Long e_uuid, String e_uuid_real, String e_tipo, String e_edApp, String e_edApp_ip, String e_accion, String e_objeto, String e_objeto_type, String e_objeto_maxScore, String e_objeto_maxAttempts, String e_objeto_maxSubmits, String e_target_generated, LocalDateTime e_datetime, Actor e_actor, String e_usuarios, String e_grupos) {
        this.e_uuid = e_uuid;
        this.e_uuid_real = e_uuid_real;
        this.e_tipo = e_tipo;
        this.e_edApp = e_edApp;
        this.e_edApp_ip = e_edApp_ip;
        this.e_accion = e_accion;
        this.e_objeto = e_objeto;
        this.e_objeto_type = e_objeto_type;
        this.e_objeto_maxScore = e_objeto_maxScore;
        this.e_objeto_maxAttempts = e_objeto_maxAttempts;
        this.e_objeto_maxSubmits = e_objeto_maxSubmits;
        this.e_target_generated = e_target_generated;
        this.e_datetime = e_datetime;
        this.e_actor = e_actor;
        this.e_usuarios = e_usuarios;
        this.e_grupos = e_grupos;
    }

    public Long getE_uuid() {
        return e_uuid;
    }

    public void setE_uuid(Long e_uuid) {
        this.e_uuid = e_uuid;
    }

    public String getE_uuid_real() {
        return e_uuid_real;
    }

    public void setE_uuid_real(String e_uuid_real) {
        this.e_uuid_real = e_uuid_real;
    }

    public String getE_tipo() {
        return e_tipo;
    }

    public void setE_tipo(String e_tipo) {
        this.e_tipo = e_tipo;
    }

    public Actor getE_actor() {
        return e_actor;
    }

    public void setE_actor(Actor e_actor) {
        this.e_actor = e_actor;
    }

    public String getE_accion() {
        return e_accion;
    }

    public void setE_accion(String e_accion) {
        this.e_accion = e_accion;
    }

    public String getE_objeto() {
        return e_objeto;
    }

    public void setE_objeto(String e_objeto) {
        this.e_objeto = e_objeto;
    }

    public LocalDateTime getE_datetime() {
        return e_datetime;
    }

    public void setE_datetime(LocalDateTime e_datetime) {
        this.e_datetime = e_datetime;
    }

    public String getE_usuarios() {
        return e_usuarios;
    }

    public void setE_usuarios(String e_usuarios) {
        this.e_usuarios = e_usuarios;
    }

    public String getE_grupos() {
        return e_grupos;
    }

    public void setE_grupos(String e_grupos) {
        this.e_grupos = e_grupos;
    }

    public String getE_edApp() {
        return e_edApp;
    }

    public void setE_edApp(String e_edApp) {
        this.e_edApp = e_edApp;
    }

    public String getE_edApp_ip() {
        return e_edApp_ip;
    }

    public void setE_edApp_ip(String e_edApp_ip) {
        this.e_edApp_ip = e_edApp_ip;
    }

    public String getE_objeto_type() {
        return e_objeto_type;
    }

    public void setE_objeto_type(String e_objeto_type) {
        this.e_objeto_type = e_objeto_type;
    }

    public String getE_objeto_maxScore() {
        return e_objeto_maxScore;
    }

    public void setE_objeto_maxScore(String e_objeto_maxScore) {
        this.e_objeto_maxScore = e_objeto_maxScore;
    }

    public String getE_objeto_maxAttempts() {
        return e_objeto_maxAttempts;
    }

    public void setE_objeto_maxAttempts(String e_objeto_maxAttempts) {
        this.e_objeto_maxAttempts = e_objeto_maxAttempts;
    }

    public String getE_objeto_maxSubmits() {
        return e_objeto_maxSubmits;
    }

    public void setE_objeto_maxSubmits(String e_objeto_maxSubmits) {
        this.e_objeto_maxSubmits = e_objeto_maxSubmits;
    }

    public String getE_target_generated() {
        return e_target_generated;
    }

    public void setE_target_generated(String e_target_generated) {
        this.e_target_generated = e_target_generated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(e_uuid, evento.e_uuid) && Objects.equals(e_uuid_real, evento.e_uuid_real) && Objects.equals(e_tipo, evento.e_tipo) && Objects.equals(e_edApp, evento.e_edApp) && Objects.equals(e_edApp_ip, evento.e_edApp_ip) && Objects.equals(e_accion, evento.e_accion) && Objects.equals(e_objeto, evento.e_objeto) && Objects.equals(e_objeto_type, evento.e_objeto_type) && Objects.equals(e_objeto_maxScore, evento.e_objeto_maxScore) && Objects.equals(e_objeto_maxAttempts, evento.e_objeto_maxAttempts) && Objects.equals(e_objeto_maxSubmits, evento.e_objeto_maxSubmits) && Objects.equals(e_target_generated, evento.e_target_generated) && Objects.equals(e_datetime, evento.e_datetime) && Objects.equals(e_actor, evento.e_actor) && Objects.equals(e_usuarios, evento.e_usuarios) && Objects.equals(e_grupos, evento.e_grupos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e_uuid, e_uuid_real, e_tipo, e_edApp, e_edApp_ip, e_accion, e_objeto, e_objeto_type, e_objeto_maxScore, e_objeto_maxAttempts, e_objeto_maxSubmits, e_target_generated, e_datetime, e_actor, e_usuarios, e_grupos);
    }
}
