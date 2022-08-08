package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoJPA extends JpaRepository<Curso, Long> {
}
