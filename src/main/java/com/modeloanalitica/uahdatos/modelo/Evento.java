package com.modeloanalitica.uahdatos.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "t_evento")
public class Evento {   // https://www.imsglobal.org/spec/caliper/v1p2#event
    @Id
    @Column(name = "e_uuid", nullable = false) //La aplicación emisora DEBE aprovisionar el evento con un UUID. SE DEBE generar un UUID de la versión 4. El UUID DEBE expresarse como un URN usando la forma "urn:uuid:<UUID>" por [RFC4122].
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long e_uuid;

    @Column(name = "e_uuid_real", nullable = false) //La aplicación emisora DEBE aprovisionar el evento con un UUID. SE DEBE generar un UUID de la versión 4. El UUID DEBE expresarse como un URN usando la forma "urn:uuid:<UUID>" por [RFC4122].
    private String e_uuid_real;

    @Column(name = "e_tipo") //Un valor de cadena correspondiente al Término definido para el evento en el documento de contexto JSON-LD de IMS Caliper externo. Para un evento genérico, establezca el valor de cadena Evento. Si se crea un subtipo de Entidad, establezca el término correspondiente al subtipo utilizado, por ejemplo, NavigationEvent.typetype
    private String e_tipo;

    @Column(name = "e_perfil") // Un valor de cadena correspondiente al valor de Término de perfil definido para el perfil que rige las reglas de interpretación de este evento. El rango de valores de perfil se limita al conjunto de perfiles descritos en esta especificación y a cualquier especificación de extensión de perfil que amplíe esta especificación. Solo se puede especificar un valor de Término de perfil por Evento. Para un evento genérico, establezca el valor de la propiedad en el término de cadena GeneralProfile.profile
    private String e_perfil;

    @Column(name = "e_accion") // La acción o predicado que une al actor o sujeto al objeto. El rango se limita al conjunto de acciones descritas en esta especificación o perfiles asociados y puede estar aún más limitado por el tipo de evento elegido. Solo se puede especificar un Término por Evento.actionaction
    private String e_accion;

    @Column(name = "e_objeto") // Entidad que comprende el objeto de la interacción. El valor DEBE expresarse como un objeto o como una cadena correspondiente al IRI del objeto.object
    private String e_objeto;

    @Column(name = "e_datetime")
    @DateTimeFormat(pattern = "YYYY-MM-DD:mm:ss") // Un valor de fecha y hora ISO 8601 expresado con precisión de milisegundos que indica cuándo ocurrió el evento. El valor DEBE expresarse utilizando el formato AAAA-MM-DDTHH:mm:ss. SSSZ establecido en UTC sin desplazamiento especificado.
    private LocalDateTime e_datetime;

    @Column(name = "e_sesion") // La sesión de usuario actual. El valor DEBE expresarse como un objeto o como una cadena correspondiente al IRI de la sesión.session
    private String e_sesion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_evento_actor", joinColumns = @JoinColumn(name = "e_uuid", referencedColumnName = "e_uuid"), inverseJoinColumns = @JoinColumn(name = "a_id", referencedColumnName = "a_id"))
    Actor e_actor; // El Agente que inició el Evento, por lo general, aunque no siempre una Persona. El valor DEBE expresarse como un objeto o como una cadena correspondiente al IRI del actor.action

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_evento_grupo", joinColumns = @JoinColumn(name = "e_uuid", referencedColumnName = "e_uuid"), inverseJoinColumns = @JoinColumn(name = "g_id", referencedColumnName = "g_id"))
    Grupo e_grupo; // Una organización que representa el contexto del grupo. El valor DEBE expresarse como un objeto o como una cadena correspondiente al IRI del grupo.group

    public Evento() {
    }

    public Evento(Long e_uuid, String e_uuid_real, String e_tipo, String e_perfil, String e_accion, String e_objeto, LocalDateTime e_datetime, String e_sesion, Actor e_actor, Grupo e_grupo) {
        this.e_uuid = e_uuid;
        this.e_uuid_real = e_uuid_real;
        this.e_tipo = e_tipo;
        this.e_perfil = e_perfil;
        this.e_accion = e_accion;
        this.e_objeto = e_objeto;
        this.e_datetime = e_datetime;
        this.e_sesion = e_sesion;
        this.e_actor = e_actor;
        this.e_grupo = e_grupo;
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

    public String getE_perfil() {
        return e_perfil;
    }

    public void setE_perfil(String e_perfil) {
        this.e_perfil = e_perfil;
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

    public Grupo getE_grupo() {
        return e_grupo;
    }

    public void setE_grupo(Grupo e_grupo) {
        this.e_grupo = e_grupo;
    }

    public String getE_sesion() {
        return e_sesion;
    }

    public void setE_sesion(String e_sesion) {
        this.e_sesion = e_sesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(e_uuid, evento.e_uuid) && Objects.equals(e_tipo, evento.e_tipo) && Objects.equals(e_perfil, evento.e_perfil) && Objects.equals(e_accion, evento.e_accion) && Objects.equals(e_objeto, evento.e_objeto) && Objects.equals(e_datetime, evento.e_datetime) && Objects.equals(e_sesion, evento.e_sesion) && Objects.equals(e_actor, evento.e_actor) && Objects.equals(e_grupo, evento.e_grupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e_uuid, e_tipo, e_perfil, e_accion, e_objeto, e_datetime, e_sesion, e_actor, e_grupo);
    }
}
