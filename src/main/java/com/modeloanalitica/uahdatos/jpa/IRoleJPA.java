package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleJPA extends JpaRepository<Role, Long> {
}
