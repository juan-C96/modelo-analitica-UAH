package com.modeloanalitica.uahdatos.controlador;

import com.modeloanalitica.uahdatos.modelo.Actor;
import com.modeloanalitica.uahdatos.modelo.Role;
import com.modeloanalitica.uahdatos.servicio.IActorService;
import com.modeloanalitica.uahdatos.servicio.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class ActorRegistrationController {

    IActorService actorService;
    IRoleService roleService;

    public ActorRegistrationController(IActorService actorService, IRoleService roleService) {
        super();
        this.actorService = actorService;
        this.roleService = roleService;
    }

    @ModelAttribute("actor")
    public Actor actor() {
        return new Actor();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registro";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("actor") Actor actor) {
        List<Role> roles = roleService.buscarTodos();
        Collection<Role> roleCollection = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            if(roles.get(i).getR_rol().equals("Instructor")){
                roleCollection.add(roles.get(i));
                actor.setA_rol(roleCollection);
            }
        }
        actorService.guardarActor(actor);

        return "redirect:/registration?success";
    }
}
