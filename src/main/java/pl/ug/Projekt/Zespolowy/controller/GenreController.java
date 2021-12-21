package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @GetMapping("/genres")
    String getGenres(Model model){
        model.addAttribute("allGenres", repository.findAll());
        return "genre-list";
    }

    @GetMapping("/genres/admin")
    String getGameAdmin(Model model){
        model.addAttribute("allGenres", repository.findAll());
        return "genre-list-admin";
    }

    @GetMapping(value = "/genre/save")
    public String genreForm(Model model) {
        model.addAttribute("Genre", new Genre());

        return "save-genre";
    }
    @PostMapping(value = "/genre/save")
    public String createUser(Model model, @ModelAttribute Genre genre) {
        repository.save(genre);

        return "redirect:/genres/admin";
    }

    @GetMapping(value = "/genre/edit/{id}")
    public String editGame(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedGenre", repository.getById(id));

        return "edit-genre";
    }

    @PostMapping("/genre/edit")
    public String editGame(@ModelAttribute("editedGame") Genre genre){
        repository.save(genre);

        return "redirect:/genres/admin";
    }

    @GetMapping("/genre/delete/{id}")
    public String deleteGame(@PathVariable("id") long id) {
        repository.deleteById(id);

        return "redirect:/genres/admin";
    }
}
