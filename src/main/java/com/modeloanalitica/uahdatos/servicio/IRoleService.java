package com.modeloanalitica.uahdatos.servicio;

import com.modeloanalitica.uahdatos.modelo.Role;

import java.util.List;

public interface IRoleService {

    List<Role> buscarTodos();

    Role buscarRolPorID(Long r_id);

    void guardarRol(Role role);

    void eliminarRol(Long r_id);

    void actualizarRol(Role role);
}
