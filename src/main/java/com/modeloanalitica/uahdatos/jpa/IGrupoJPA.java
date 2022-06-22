package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGrupoJPA extends JpaRepository<Grupo, Long> {
}
