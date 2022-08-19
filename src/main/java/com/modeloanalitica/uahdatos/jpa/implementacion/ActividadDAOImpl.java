package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IActividadJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IActividadDAO;
import com.modeloanalitica.uahdatos.modelo.Actividad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActividadDAOImpl implements IActividadDAO {

    @Autowired
    IActividadJPA actividadJPA;

    public ActividadDAOImpl(IActividadJPA actividadJPA) {
        super();
        this.actividadJPA = actividadJPA;
    }

    @Override
    public List<Actividad> buscarTodos() {
        return actividadJPA.findAll();
    }

    @Override
    public Actividad buscarActividadPorId(Long activity_id) {
        Optional<Actividad> optional = actividadJPA.findById(activity_id);
        if(optional.isPresent()){
            return  optional.get();
        }
        return null;
    }

    @Override
    public void guardarActividad(Actividad actividad) {
        actividadJPA.save(actividad);
    }

    @Override
    public void eliminarActividad(Long activity_id) {
        Optional<Actividad> optional = actividadJPA.findById(activity_id);
        if(optional.isPresent()){
            actividadJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarActividad(Actividad actividad) {
        actividadJPA.save(actividad);
    }
}
