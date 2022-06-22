package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.Grupo;

import java.util.List;

public interface IGrupoDAO {

    List<Grupo> buscarTodos();

    Grupo buscarGrupoPorId(Long g_id);

    //SE DESPRECIAN PUES NO ES REQUISITO DEL SISTEMA
    void guardarGrupo(Grupo grupo);

    void eliminarGrupo(Long g_id);

    void actualizarGrupo(Grupo grupo);
}
