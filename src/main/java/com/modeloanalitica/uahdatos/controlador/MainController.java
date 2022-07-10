package com.modeloanalitica.uahdatos.controlador;

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

    public MainController(IEventoService eventoService) {
        super();
        this.eventoService = eventoService;
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
}
