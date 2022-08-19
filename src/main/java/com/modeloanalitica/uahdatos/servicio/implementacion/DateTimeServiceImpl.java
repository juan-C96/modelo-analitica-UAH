package com.modeloanalitica.uahdatos.servicio.implementacion;

import com.modeloanalitica.uahdatos.jpa.dao.IDateTimeDAO;
import com.modeloanalitica.uahdatos.modelo.Datetime;
import com.modeloanalitica.uahdatos.servicio.IDateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateTimeServiceImpl implements IDateTimeService {

    @Autowired
    IDateTimeDAO dateTimeDAO;

    public DateTimeServiceImpl(IDateTimeDAO dateTimeDAO) {
        super();
        this.dateTimeDAO = dateTimeDAO;
    }

    @Override
    public List<Datetime> buscarTodos() {
        return dateTimeDAO.buscarTodos();
    }

    @Override
    public Datetime buscarDateTimePorId(Long date_id) {
        return dateTimeDAO.buscarDatetimePorId(date_id);
    }

    @Override
    public void guardarDateTime(Datetime dateTime) {
        dateTimeDAO.guardarDatetime(dateTime);
    }

    @Override
    public void eliminarDateTime(Long date_id) {
        dateTimeDAO.eliminarDatetime(date_id);
    }

    @Override
    public void actualizarDateTime(Datetime dateTime) {
        dateTimeDAO.actualizarDatetime(dateTime);
    }
}
