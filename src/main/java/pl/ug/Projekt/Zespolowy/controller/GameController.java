package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.Game;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;


@Controller
public class GameController {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;

    GameController(GameRepository repository, GenreRepository genreRepository, PublisherRepository publisherRepository){
        this.gameRepository = repository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/games")
    String getGame(Model model){
        model.addAttribute("allGames",gameRepository.findAll());
        return "game-list";
    }



    @GetMapping(value = "/saveGame")
    public String addGame(ModelMap model) {
        model.addAttribute("newGame", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "save-game";
    }


    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute("saveGame") Game game, ModelMap model){
        gameRepository.save(game);
        model.addAttribute("newGame", gameRepository.findAll());
        return "index";
    }

}

