package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.Role;

import java.util.List;

public interface IRoleDAO {

    List<Role> buscarTodos();

    Role buscarRolPorId(Long r_id);

    void guardarRol(Role rol);

    void eliminarRol(Long r_id);

    void actualizarRol(Role rol);
}
