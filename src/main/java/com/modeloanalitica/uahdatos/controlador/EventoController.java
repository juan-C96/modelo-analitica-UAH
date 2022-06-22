package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Evento;
import com.modeloanalitica.uahdatos.servicio.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    IEventoService eventoService;

    public EventoController(IEventoService eventoService) {
        super();
        this.eventoService = eventoService;
    }

    @GetMapping("/eventos")
    public List<Evento> buscarTodos() {
        return eventoService.buscarTodos();
    }

    @GetMapping("/eventos/{e_uuid}")
    public Evento buscarEventoPorId(@PathVariable("e_uuid") Long e_uuid) {
        return eventoService.buscarEventoPorId(e_uuid);
    }

    @PostMapping("/eventosS")
    public void guardarEvento(@RequestBody Evento evento) {
        eventoService.guardarEvento(evento);
    }

    @PutMapping("/eventosU")
    public void actualizarEvento(@RequestBody Evento evento) {
        eventoService.actualizarEvento(evento);
    }
    @DeleteMapping("/eventos/{e_uuid}")
    public void eliminarEvento(@PathVariable("e_uuid") Long e_uuid) {
        eventoService.eliminarEvento(e_uuid);
    }
}
