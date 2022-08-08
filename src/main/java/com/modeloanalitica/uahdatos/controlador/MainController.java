package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import com.modeloanalitica.uahdatos.servicio.ICursoService;
import com.modeloanalitica.uahdatos.servicio.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    IEventoService eventoService;
    @Autowired
    IActorService actorService;
    @Autowired
    ICursoService cursoService;

    public MainController(IEventoService eventoService, IActorService actorService, ICursoService cursoService) {
        this.eventoService = eventoService;
        this.actorService = actorService;
        this.cursoService = cursoService;
    }

    @GetMapping("/")
    public String MainIndex(Model model) {

        model.addAttribute("eventos", eventoService.buscarTodos());

        return "index";
    }

    @GetMapping("/estadisticas")
    public String Estadisticas(Model model) {

        List<Actor> estudiantes = new ArrayList<>();
        for (int i = 0; i < actorService.buscarTodos().size(); i++) {
            if(actorService.buscarTodos().get(i).getA_roles().equals("Learner")){
                estudiantes.add(actorService.buscarTodos().get(i));
            }
        }

        if(cursoService.buscarTodos().size() > 0){
            model.addAttribute("t_horas", cursoService.buscarTodos().get(0).getC_tiempo_horas());
            model.addAttribute("actividades", cursoService.buscarTodos().get(0).getC_numero_actividades());
            model.addAttribute("actividades_terminadas", cursoService.buscarTodos().get(0).getC_numero_actividades_terminadas());
        } else {
            model.addAttribute("t_horas", 0);
            model.addAttribute("actividades", 0);
            model.addAttribute("actividades_terminadas", 0);
        }

        model.addAttribute("n_estudiantes", estudiantes.size());

        return "estadisticas";
    }

    @GetMapping("/aactores")
    public String Actores(Model model) {
        model.addAttribute("actores", actorService.buscarTodos());
        return "actores/actores";
    }

    @GetMapping("/eestudiantes")
    public String Estudiantes(Model model) {
        List<Actor> estudiantes = new ArrayList<>();
        for (int i = 0; i < actorService.buscarTodos().size(); i++) {
            if(actorService.buscarTodos().get(i).getA_roles().equals("Learner")){
                estudiantes.add(actorService.buscarTodos().get(i));
            }
        }
        model.addAttribute("estudiantes", estudiantes);
        return "actores/estudiantes";
    }

    @GetMapping("/ggrupos")
    public String Grupo(Model model) {
        model.addAttribute("cursos", cursoService.buscarTodos());
        return "grupo/grupos";
    }


}
