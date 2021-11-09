package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;


@Controller
public class GenreController {

    private final GenreRepository repository;

    GenreController(GenreRepository repository){
        this.repository = repository;
    }
    @RequestMapping(value = "/saveGenre", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("Genre", new Genre());
        return "saveGenre";
    }
    @RequestMapping(value = "/saveGenre", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute Genre genre) {
        repository.save(genre);
        return "redirect:/saveGenre/";
    }
}
