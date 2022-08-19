package com.modeloanalitica.uahdatos.jpa;

import com.modeloanalitica.uahdatos.modelo.Datetime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDateTimeJPA extends JpaRepository<Datetime, Long> {
}
