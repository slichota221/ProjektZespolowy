package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;

import java.util.List;


@Controller
public class GenreController {

    private final GenreRepository repository;

    GenreController(GenreRepository repository){
        this.repository = repository;
    }


    @GetMapping(value = "/saveGenre")
    public String genreForm(Model model) {
        model.addAttribute("Genre", new Genre());

        return "save-genre";
    }
    @PostMapping(value = "/saveGenre")
    public String createUser(Model model, @ModelAttribute Genre genre) {
        repository.save(genre);
        return "redirect:/saveGenre/";
    }
}
