package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorJPA extends JpaRepository<Actor, Long> {
}
