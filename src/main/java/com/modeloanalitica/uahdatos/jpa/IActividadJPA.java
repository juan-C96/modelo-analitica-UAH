package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActividadJPA extends JpaRepository<Actividad, Long> {
}
