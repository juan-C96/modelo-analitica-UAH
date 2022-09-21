package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IRoleDAO;
import com.modeloanalitica.uahdatos.jpa.dao.IUserDAO;
import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.modelo.User;
import com.modeloanalitica.uahdatos.servicio.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private IUserDAO userRepository;

    @Autowired
    IRoleDAO roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserDAO userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(User userReg) {
        Collection<Role> roles = new ArrayList<>();
        List<Role> listRoles = roleRepo.buscarTodos();
        boolean flag = true;
        Long id_rol = null;
        for (int i = 0; i < listRoles.size(); i++) {
            if (listRoles.get(i).getR_rol().equals("Instructor")) {
                id_rol = listRoles.get(i).getR_id();
                flag = false;
                break;
            }
        }

        if (flag) {
            Role rol = new Role();
            rol.setR_rol("Instructor");
            roleRepo.guardarRol(rol);
            listRoles = roleRepo.buscarTodos();
            for (int i = 0; i < listRoles.size(); i++) {
                if (listRoles.get(i).getR_rol().equals("Instructor")) {
                    id_rol = listRoles.get(i).getR_id();
                    break;
                }
            }
        }

        Role rol_assig = roleRepo.buscarRolPorId(id_rol);
        roles.add(rol_assig);

        userReg.setRoles(roles);

        User user = new User(userReg.getName(), userReg.getEmail(), userReg.getEmail(),
                passwordEncoder.encode(userReg.getPassword()), true, userReg.getUser_curso(), userReg.getRoles());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null || user.isEnabled() == false) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getR_rol())).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String getEmailS(Long id) {
        return getUserById(id).getEmail();
    }

    public List<Role> getRoles() {
        return roleRepo.buscarTodos();
    }


    public User getUserByName(String name) {

        List<User> usuarios = userRepository.findAll();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getName().equals(name)) {
                return usuarios.get(i);
            }
        }

        return null;
    }
}

