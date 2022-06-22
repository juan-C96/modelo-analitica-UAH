package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Evento;

import java.util.List;

public interface IEventoService {

    List<Evento> buscarTodos();

    Evento buscarEventoPorId(Long e_uuid);

    //SE DESPRECIAN PUES NO ES REQUISITO DEL SISTEMA
    void guardarEvento(Evento evento);

    void eliminarEvento(Long e_uuid);

    void actualizarEvento(Evento evento);

}
