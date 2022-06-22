package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Actor;

import java.util.List;

public interface IActorService {

    List<Actor> buscarTodos();

    Actor buscarActorPorId(Long a_id);

    //SE DESPRECIAN PUES NO ES REQUISITO DEL SISTEMA
    void guardarActor(Actor actor);

    void eliminarActor(Long a_id);

    void actualizarActor(Actor actor);

}
