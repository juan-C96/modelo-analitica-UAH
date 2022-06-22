package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IEventoJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IEventoDAO;
import com.modeloanalitica.uahdatos.modelo.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventoDAOImpl implements IEventoDAO {

    @Autowired
    IEventoJPA eventoJPA;

    public EventoDAOImpl(IEventoJPA eventoJPA) {
        super();
        this.eventoJPA = eventoJPA;
    }

    @Override
    public List<Evento> buscarTodos() {
        return eventoJPA.findAll();
    }

    @Override
    public Evento buscarEventoPorId(Long e_uuid) {
        Optional<Evento> optional = eventoJPA.findById(e_uuid);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarEvento(Evento evento) {
        eventoJPA.save(evento);
    }

    @Override
    public void eliminarEvento(Long e_uuid) {
        Optional<Evento> optional = eventoJPA.findById(e_uuid);
        if(optional.isPresent()){
            eventoJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarEvento(Evento evento) {
        eventoJPA.save(evento);
    }
}
