package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IActorDAO;
import com.modeloanalitica.uahdatos.jpa.dao.IRoleDAO;
import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    IActorDAO actorDAO;

    @Autowired
    IRoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ActorServiceImpl(IActorDAO actorDAO, IRoleDAO roleDAO, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.actorDAO = actorDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Actor> buscarTodos() {
        return actorDAO.buscarTodos();
    }

    @Override
    public Actor buscarActorPorId(Long a_id) {
        return actorDAO.buscarActorPorId(a_id);
    }

    @Override
    public void guardarActor(Actor actor) {
        actorDAO.guardarActor(actor);
    }

    @Override
    public void eliminarActor(Long a_id) {
        actorDAO.eliminarActor(a_id);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actorDAO.actualizarActor(actor);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Actor actor = actorDAO.buscarPorUsuario(username);

        if (actor == null) {
            throw new UsernameNotFoundException("Usuario no existe.");
        } else {
            System.out.println("Usuario " + actor.getA_usuario());

            if(actor.getA_usuario().equals("Instructor")){return new org.springframework.security.core.userdetails.User(actor.getEmail(), "instructor",
                    mapRolesToAuthorities(actor.getA_rol()));
            }
        }

        return new org.springframework.security.core.userdetails.User(actor.getEmail(), actor.getPassword(),
                mapRolesToAuthorities(actor.getA_rol()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(t_role -> new SimpleGrantedAuthority(t_role.getR_rol())).collect(Collectors.toList());
    }
}
