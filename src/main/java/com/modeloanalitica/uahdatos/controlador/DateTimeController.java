package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Datetime;
import com.modeloanalitica.uahdatos.servicio.IDateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DateTimeController {

    @Autowired
    IDateTimeService dateTimeService;

    public DateTimeController(IDateTimeService dateTimeService) {
        super();
        this.dateTimeService = dateTimeService;
    }

    @GetMapping("/datetime")
    public List<Datetime> buscarTodos() {
        return dateTimeService.buscarTodos();
    }

    @GetMapping("/datetime/{date_id}")
    public Datetime buscarDateTimePorId(@PathVariable("date_id") Long date_id) {
        return dateTimeService.buscarDateTimePorId(date_id);
    }

    @PostMapping("/datetimeS")
    public void guardarDatetime(@RequestBody Datetime datetime) {
        dateTimeService.guardarDateTime(datetime);
    }

    @PutMapping("/datetimeU")
    public void actualizarDatetime(@RequestBody Datetime datetime) {
        dateTimeService.actualizarDateTime(datetime);
    }

    @DeleteMapping("/datetime/{date_id}")
    public void eliminarDatetime(@PathVariable("date_id") Long date_id) {
        dateTimeService.eliminarDateTime(date_id);
    }

}
