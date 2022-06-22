package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IGrupoDAO;
import com.modeloanalitica.uahdatos.modelo.Grupo;
import com.modeloanalitica.uahdatos.servicio.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    IGrupoDAO grupoDAO;

    @Override
    public List<Grupo> buscarTodos() {
        return grupoDAO.buscarTodos();
    }

    @Override
    public Grupo buscarGrupoPorId(Long g_id) {
        return grupoDAO.buscarGrupoPorId(g_id);
    }

    @Override
    public void guardarGrupo(Grupo grupo) {
        grupoDAO.guardarGrupo(grupo);
    }

    @Override
    public void eliminarGrupo(Long g_id) {
        grupoDAO.eliminarGrupo(g_id);
    }

    @Override
    public void actualizarGrupo(Grupo grupo) {
        grupoDAO.actualizarGrupo(grupo);
    }
}
