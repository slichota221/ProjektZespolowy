package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.Game;
import pl.ug.Projekt.Zespolowy.exception.GameNotFoundException;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;


@Controller
public class GameController {

    private final GameRepository repository;

    GameController(GameRepository repository){
        this.repository = repository;
    }

    @GetMapping("/games")
    String getGame(Model model){
        model.addAttribute("allGames",repository.findAll());
        return "game-list";
    }

    @PutMapping("/game/{id}")
    Game replaceGame(@RequestBody Game newGame, @PathVariable Long id){

        return repository.findById(id)
                .map(employee -> {
                    employee.setNameGame(newGame.getNameGame());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newGame.setID(id);
                    return repository.save(newGame);
                });
    }

    @DeleteMapping("/game/{id}")
    void deleteGame(@PathVariable Long id){
        repository.deleteById(id);
    }

}

