package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IRoleDAO;
import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.servicio.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDAO roleDAO;

    @Override
    public List<Role> buscarTodos() {
        return roleDAO.buscarTodos();
    }

    @Override
    public Role buscarRolPorID(Long r_id) {
        return roleDAO.buscarRolPorId(r_id);
    }

    @Override
    public void guardarRol(Role role) {
        roleDAO.guardarRol(role);
    }

    @Override
    public void eliminarRol(Long r_id) {
        roleDAO.eliminarRol(r_id);
    }

    @Override
    public void actualizarRol(Role role) {
        roleDAO.actualizarRol(role);
    }
}
