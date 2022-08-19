package com.modeloanalitica.uahdatos.jpa.implementacion;

import com.modeloanalitica.uahdatos.jpa.IDateTimeJPA;
import com.modeloanalitica.uahdatos.jpa.dao.IDateTimeDAO;
import com.modeloanalitica.uahdatos.modelo.Datetime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DateTimeDAOImpl implements IDateTimeDAO {

    @Autowired
    IDateTimeJPA dateTimeJPA;

    public DateTimeDAOImpl(IDateTimeJPA dateTimeJPA) {
        super();
        this.dateTimeJPA = dateTimeJPA;
    }

    @Override
    public List<Datetime> buscarTodos() {
        return dateTimeJPA.findAll();
    }

    @Override
    public Datetime buscarDatetimePorId(Long date_id) {
        Optional<Datetime> optional = dateTimeJPA.findById(date_id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardarDatetime(Datetime datetime) {
        dateTimeJPA.save(datetime);
    }

    @Override
    public void eliminarDatetime(Long date_id) {
        Optional<Datetime> optional = dateTimeJPA.findById(date_id);
        if(optional.isPresent()){
            dateTimeJPA.delete(optional.get());
        }
    }

    @Override
    public void actualizarDatetime(Datetime datetime) {
        dateTimeJPA.save(datetime);
    }
}
