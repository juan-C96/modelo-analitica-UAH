package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Datetime;

import java.util.List;

public interface IDateTimeService {

    List<Datetime> buscarTodos();

    Datetime buscarDateTimePorId(Long date_id);

    void guardarDateTime(Datetime dateTime);

    void eliminarDateTime(Long date_id);

    void actualizarDateTime(Datetime dateTime);
}
