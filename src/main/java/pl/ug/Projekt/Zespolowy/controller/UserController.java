package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.User;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;
import pl.ug.Projekt.Zespolowy.repository.UserRepository;

import java.security.Principal;

@Controller
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @GetMapping("/web/user")
    String getUser(Model model, Principal principal) {
        model.addAttribute("viewedUser", userRepository.findByUsername(principal.getName()));

        return "user-profile";
    }

    @GetMapping("/web/users")
    String getUsers(Model model){
        model.addAttribute("allUsers", userRepository.findAll());

        return "user-list";
    }

    @GetMapping("/web/user/{id}")
    String getUser(@PathVariable("id") long id, Model model){
        model.addAttribute("viewedUser", userRepository.getById(id));

        return "user-profile";
    }

}
