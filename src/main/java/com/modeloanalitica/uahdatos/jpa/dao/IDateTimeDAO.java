package com.modeloanalitica.uahdatos.jpa.dao;


import com.modeloanalitica.uahdatos.modelo.Datetime;

import java.util.List;

public interface IDateTimeDAO {
    List<Datetime> buscarTodos();

    Datetime buscarDatetimePorId(Long date_id);

    void guardarDatetime(Datetime datetime);

    void eliminarDatetime(Long date_id);

    void actualizarDatetime(Datetime datetime);
}
