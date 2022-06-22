package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IGrupoJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IGrupoDAO;
import com.modeloanalitica.uahdatos.modelo.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GrupoDAOImpl implements IGrupoDAO {

    @Autowired
    IGrupoJPA grupoJPA;

    public GrupoDAOImpl(IGrupoJPA grupoJPA) {
        super();
        this.grupoJPA = grupoJPA;
    }

    @Override
    public List<Grupo> buscarTodos() {
        return grupoJPA.findAll();
    }

    @Override
    public Grupo buscarGrupoPorId(Long g_id) {
        Optional<Grupo> optional = grupoJPA.findById(g_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarGrupo(Grupo grupo) {
        grupoJPA.save(grupo);
    }

    @Override
    public void eliminarGrupo(Long g_id) {
        Optional<Grupo> optional = grupoJPA.findById(g_id);
        if(optional.isPresent()){
            grupoJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarGrupo(Grupo grupo) {
        grupoJPA.save(grupo);
    }
}
