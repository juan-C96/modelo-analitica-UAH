package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.Actividad;

import java.util.List;

public interface IActividadDAO {

    List<Actividad> buscarTodos();

    Actividad buscarActividadPorId(Long activity_id);

    void guardarActividad(Actividad actividad);

    void eliminarActividad(Long activity_id);

    void actualizarActividad(Actividad actividad);
}
