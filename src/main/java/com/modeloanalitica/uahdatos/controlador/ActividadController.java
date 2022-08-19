package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Actividad;
import com.modeloanalitica.uahdatos.servicio.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActividadController {

    @Autowired
    IActividadService actividadService;

    public ActividadController(IActividadService actividadService) {
        super();
        this.actividadService = actividadService;
    }

    @GetMapping("/actividades")
    public List<Actividad> buscarTodos() {
        return actividadService.buscarTodos();
    }

    @GetMapping("/actividad/{activity_id}")
    public Actividad buscarActividadPorId(@PathVariable("activity_id") Long activity_id) {
        return actividadService.buscarActividadPorId(activity_id);
    }

    @PostMapping("/actividadesS")
    public void guardarActividad(@RequestBody Actividad actividad) {
        actividadService.guardarActividad(actividad);
    }

    @PutMapping("/actividadU")
    public void actualizarActividad(@RequestBody Actividad actividad) {
        actividadService.actualizarActividad(actividad);
    }

    @DeleteMapping("/actividad/{activity_id}")
    public void eliminarActividad(@PathVariable("activity_id") Long activity_id) {
        actividadService.eliminarActividad(activity_id);
    }
}
