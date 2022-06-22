package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IEventoDAO;
import com.modeloanalitica.uahdatos.modelo.Evento;
import com.modeloanalitica.uahdatos.servicio.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    IEventoDAO eventoDAO;

    @Override
    public List<Evento> buscarTodos() {
        return eventoDAO.buscarTodos();
    }

    @Override
    public Evento buscarEventoPorId(Long e_uuid) {
        return eventoDAO.buscarEventoPorId(e_uuid);
    }

    @Override
    public void guardarEvento(Evento evento) {
        eventoDAO.guardarEvento(evento);
    }

    @Override
    public void eliminarEvento(Long e_uuid) {
        eventoDAO.eliminarEvento(e_uuid);
    }

    @Override
    public void actualizarEvento(Evento evento) {
        eventoDAO.actualizarEvento(evento);
    }
}
