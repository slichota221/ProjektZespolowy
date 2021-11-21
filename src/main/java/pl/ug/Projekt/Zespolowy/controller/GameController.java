package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.Game;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;


@Controller
public class GameController {

    private final GameRepository repository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    GameController(GameRepository repository, GenreRepository genreRepository, PublisherRepository publisherRepository){
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/games")
    String getGame(Model model){
        model.addAttribute("allGames",repository.findAll());
        return "game-list";
    }



    @GetMapping(value = "/saveGame")
    public String gameForm(ModelMap model) {
        model.addAttribute("Game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "save-game";
    }


}

