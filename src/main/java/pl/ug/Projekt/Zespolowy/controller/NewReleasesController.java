package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ug.Projekt.Zespolowy.service.GameService;

@Controller
@RequestMapping(value = "/new-releases")
public class NewReleasesController {

    private final GameService gameService;

    public NewReleasesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    String getGame(Model model){
        model.addAttribute("allGames", gameService.getGamesReleasedLast7Days());
        return "game-list-new";
    }
}
