package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IActorDAO;
import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    IActorDAO actorDAO;

    @Override
    public List<Actor> buscarTodos() {
        return actorDAO.buscarTodos();
    }

    @Override
    public Actor buscarActorPorId(Long a_id) {
        return actorDAO.buscarActorPorId(a_id);
    }

    @Override
    public void guardarActor(Actor actor) {
        actorDAO.guardarActor(actor);
    }

    @Override
    public void eliminarActor(Long a_id) {
        actorDAO.eliminarActor(a_id);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actorDAO.actualizarActor(actor);
    }
}
