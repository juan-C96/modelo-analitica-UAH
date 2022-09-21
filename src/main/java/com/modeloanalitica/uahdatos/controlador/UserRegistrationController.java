package com.modeloanalitica.uahdatos.controlador;

import java.util.List;

import com.modeloanalitica.uahdatos.modelo.User;
import com.modeloanalitica.uahdatos.servicio.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private IUserService userService;

    public UserRegistrationController(IUserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registro";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/registration?success";
    }
}
