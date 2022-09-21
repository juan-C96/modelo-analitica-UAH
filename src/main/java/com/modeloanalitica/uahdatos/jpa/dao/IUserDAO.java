package com.modeloanalitica.uahdatos.jpa.dao;

import com.modeloanalitica.uahdatos.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUser(String user);
}
