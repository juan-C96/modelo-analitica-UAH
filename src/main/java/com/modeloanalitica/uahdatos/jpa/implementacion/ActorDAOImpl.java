package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IActorJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IActorDAO;
import com.modeloanalitica.uahdatos.modelo.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDAOImpl implements IActorDAO {

    @Autowired
    IActorJPA actorJPA;

    public ActorDAOImpl(IActorJPA actorJPA) {
        super();
        this.actorJPA = actorJPA;
    }

    @Override
    public List<Actor> buscarTodos() {
        return actorJPA.findAll();
    }

    @Override
    public Actor buscarActorPorId(Long a_id) {
        Optional<Actor> optional = actorJPA.findById(a_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarActor(Actor actor) {
        actorJPA.save(actor);
    }

    @Override
    public void eliminarActor(Long a_id) {
        Optional<Actor> optional = actorJPA.findById(a_id);
        if(optional.isPresent()){
            actorJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarActor(Actor actor) {
        actorJPA.save(actor);
    }
}
