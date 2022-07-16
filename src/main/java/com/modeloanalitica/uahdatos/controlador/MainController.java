package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.servicio.IActorService;
import com.modeloanalitica.uahdatos.servicio.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @Autowired
    IEventoService eventoService;
    @Autowired
    IActorService actorService;

    public MainController(IEventoService eventoService, IActorService actorService) {
        super();
        this.eventoService = eventoService;
        this.actorService = actorService;
    }

    @GetMapping("/")
    public String MainIndex(Model model) {
        model.addAttribute("eventos", eventoService.buscarTodos());
        return "index";
    }

    @GetMapping("/estadisticas")
    public String Estadisticas(Model model) {
        model.addAttribute("eventos", eventoService.buscarTodos());
        return "estadisticas";
    }

    @GetMapping("/aactores")
    public String Actores(Model model) {
        model.addAttribute("actores", actorService.buscarTodos());
        return "actores/actores";
    }
}
