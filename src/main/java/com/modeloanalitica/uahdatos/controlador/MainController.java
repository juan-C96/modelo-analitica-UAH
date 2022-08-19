package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.modelo.Curso;
import com.modeloanalitica.uahdatos.modelo.Datetime;
import com.modeloanalitica.uahdatos.modelo.Evento;
import com.modeloanalitica.uahdatos.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    IEventoService eventoService;
    @Autowired
    IActorService actorService;
    @Autowired
    ICursoService cursoService;
    @Autowired
    IActividadService actividadService;
    @Autowired
    IDateTimeService dateTimeService;

    public MainController(IEventoService eventoService, IActorService actorService, ICursoService cursoService, IActividadService actividadService, IDateTimeService dateTimeService) {
        this.eventoService = eventoService;
        this.actorService = actorService;
        this.cursoService = cursoService;
        this.actividadService = actividadService;
        this.dateTimeService = dateTimeService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String MainIndex(Model model) {
        List<Evento> eventos = eventoService.buscarTodos();
        List<Datetime> datetimes = dateTimeService.buscarTodos();

        List<Actor> estudiantes = new ArrayList<>();
        for (int i = 0; i < actorService.buscarTodos().size(); i++) {
            if(actorService.buscarTodos().get(i).getA_roles().equals("Learner")){
                estudiantes.add(actorService.buscarTodos().get(i));
            }
        }

        if(cursoService.buscarTodos().size() > 0){
            Curso curso = cursoService.buscarTodos().get(0);
            String f_inicio = curso.getC_fechaInicio().toString().substring(0,10);
            String f_fin = curso.getC_fechafin().toString().substring(0,10);
            model.addAttribute("f_inicio", f_inicio);
            model.addAttribute("f_fin", f_fin);
            model.addAttribute("t_horas", curso.getC_tiempo_horas());
            model.addAttribute("actividades", curso.getC_numero_actividades());
            model.addAttribute("actividades_terminadas", curso.getC_numero_actividades_terminadas());
            model.addAttribute("n_estudiantes", estudiantes.size());

            List<String> fechas = new ArrayList<>();
            for (int i = 0; i < datetimes.size(); i++) {
                fechas.add(datetimes.get(i).getDate_fecha());
            }
            Collections.sort(fechas);

            ArrayList<Integer> cant_conectados_por_dias = new ArrayList<>();
            for (int i = 0; i < fechas.size(); i++) {
                for (int j = 0; j < datetimes.size(); j++) {
                    if(datetimes.get(j).getDate_fecha().equals(fechas.get(i))){
                        cant_conectados_por_dias.add(datetimes.get(j).getDate_personas().size());
                    }
                }
            }

            model.addAttribute("fechas", fechas);
            model.addAttribute("conectados_por_dia", cant_conectados_por_dias);
        } else {
            model.addAttribute("conectados_por_dia", 0);
            model.addAttribute("f_inicio", "-" );
            model.addAttribute("f_fin", "-" );
            model.addAttribute("t_horas", 0);
            model.addAttribute("actividades", 0);
            model.addAttribute("actividades_terminadas", 0);
            model.addAttribute("n_estudiantes", 0);
        }


        return "index";
    }

    @GetMapping("/eeventos")
    public String Eventos(Model model) {

        model.addAttribute("eventos", eventoService.buscarTodos());

        return "eventos";
    }

    @GetMapping("/aactores")
    public String Actores(Model model) {
        model.addAttribute("actores", actorService.buscarTodos());
        return "integrantes";
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
        return "estudiantes";
    }

    @GetMapping("/ggrupos")
    public String Grupo(Model model) {
        model.addAttribute("cursos", cursoService.buscarTodos());
        return "grupos";
    }

    @GetMapping("/aactividades")
    public String Actividad(Model model) {
        model.addAttribute("actividades", actividadService.buscarTodos());
        return "actividades";
    }

    @GetMapping("/ddias")
    public String Dias(Model model) {
        model.addAttribute("datetimes", dateTimeService.buscarTodos());
        return "dias";
    }
}
