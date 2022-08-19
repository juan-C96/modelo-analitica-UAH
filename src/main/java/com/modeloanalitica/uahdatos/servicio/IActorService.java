package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Actor;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IActorService extends UserDetailsService {

    List<Actor> buscarTodos();

    Actor buscarActorPorId(Long a_id);

    void guardarActor(Actor actor);

    void eliminarActor(Long a_id);

    void actualizarActor(Actor actor);

}
