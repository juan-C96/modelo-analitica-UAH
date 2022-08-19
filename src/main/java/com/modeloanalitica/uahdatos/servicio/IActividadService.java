package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Actividad;

import java.util.List;

public interface IActividadService {

    List<Actividad> buscarTodos();

    Actividad buscarActividadPorId(Long activity_id);

    void guardarActividad(Actividad actividad);

    void eliminarActividad(Long activity_id);

    void actualizarActividad(Actividad actividad);
}
