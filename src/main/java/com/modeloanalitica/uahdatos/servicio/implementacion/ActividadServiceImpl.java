package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IActividadDAO;
import com.modeloanalitica.uahdatos.modelo.Actividad;
import com.modeloanalitica.uahdatos.servicio.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    IActividadDAO actividadDAO;

    @Override
    public List<Actividad> buscarTodos() {
        return actividadDAO.buscarTodos();
    }

    @Override
    public Actividad buscarActividadPorId(Long activity_id) {
        return actividadDAO.buscarActividadPorId(activity_id);
    }

    @Override
    public void guardarActividad(Actividad actividad) {
        actividadDAO.guardarActividad(actividad);
    }

    @Override
    public void eliminarActividad(Long activity_id) {
        actividadDAO.eliminarActividad(activity_id);
    }

    @Override
    public void actualizarActividad(Actividad actividad) {
        actividadDAO.actualizarActividad(actividad);
    }
}
