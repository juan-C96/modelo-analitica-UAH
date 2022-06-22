package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    IActorService actorService;

    public ActorController(IActorService actorService) {
        super();
        this.actorService = actorService;
    }

    @GetMapping("/actores")
    public List<Actor> buscarTodos() {
        return actorService.buscarTodos();
    }

    @GetMapping("/actores/{a_id}")
    public Actor buscarActorPorId(@PathVariable("a_id") Long a_id) {
        return actorService.buscarActorPorId(a_id);
    }

    @PostMapping("/actoresS")
    public void guardarActor(@RequestBody Actor actor) {
        actorService.guardarActor(actor);
    }

    @PutMapping("/actoresU")
    public void actualizarActor(@RequestBody Actor actor) {
        actorService.actualizarActor(actor);
/*
        Actor actor = iActorService.getActorById(actorDetails.getActor_id());
        if (actor != null) {
            actor.setName(actorDetails.getName());
            actor.setF_born(actorDetails.getF_born());
            actor.setCountry(actorDetails.getCountry());
            iActorService.updateActor(actor);
        }else{
            iActorService.saveActor(actorDetails);
        }
        return ResponseEntity.ok().build();*/
    }
    @DeleteMapping("/actores/{a_id}")
    public void eliminarActor(@PathVariable("a_id") Long a_id) {
        actorService.eliminarActor(a_id);
    }
}
