package com.modeloanalitica.uahdatos.servicio;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.modelo.User;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByName(String name);

    String getEmailS(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);

    User save(User user);

    List<Role> getRoles();
}
