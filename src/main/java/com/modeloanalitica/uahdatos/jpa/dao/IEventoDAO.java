package com.modeloanalitica.uahdatos.jpa.dao;


import com.modeloanalitica.uahdatos.modelo.Evento;
import java.util.List;

public interface IEventoDAO {

    List<Evento> buscarTodos();

    Evento buscarEventoPorId(Long e_uuid);

    void guardarEvento(Evento evento);

    void eliminarEvento(Long e_uuid);

    void actualizarEvento(Evento evento);
}
