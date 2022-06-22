package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IEventoJPA extends JpaRepository<Evento, Long> {
}
