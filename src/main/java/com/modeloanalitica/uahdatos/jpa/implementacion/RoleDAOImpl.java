package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IRoleJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IRoleDAO;
import com.modeloanalitica.uahdatos.modelo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDAOImpl implements IRoleDAO {

    @Autowired
    IRoleJPA roleJPA;

    public RoleDAOImpl(IRoleJPA roleJPA) {
        this.roleJPA = roleJPA;
    }

    @Override
    public List<Role> buscarTodos() {
        return roleJPA.findAll();
    }

    @Override
    public Role buscarRolPorId(Long r_id) {
        Optional<Role> optional = roleJPA.findById(r_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarRol(Role rol) {
        roleJPA.save(rol);
    }

    @Override
    public void eliminarRol(Long r_id) {
        Optional<Role> optional = roleJPA.findById(r_id);
        if(optional.isPresent()){
            roleJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarRol(Role rol) {
        roleJPA.save(rol);
    }
}
