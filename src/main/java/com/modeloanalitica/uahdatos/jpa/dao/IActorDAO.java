package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.Actor;

import java.util.List;

public interface IActorDAO {

    List<Actor> buscarTodos();

    Actor buscarActorPorId(Long a_id);

    Actor buscarPorUsuario(String username);

    void guardarActor(Actor actor);

    void eliminarActor(Long a_id);

    void actualizarActor(Actor actor);

}
