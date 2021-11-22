package pl.ug.Projekt.Zespolowy.restcontroller;

import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.domain.Game;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;

@RestController

public class GameRestController {
    private final GameRepository repository;

    public GameRestController(GameRepository repository) {
        this.repository = repository;
    }
    @DeleteMapping("/game/{id}")
    void deleteGame(@PathVariable Long id){
        repository.deleteById(id);
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
}
