package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ug.Projekt.Zespolowy.domain.Console;
import pl.ug.Projekt.Zespolowy.domain.Game;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.ConsoleRepository;
import pl.ug.Projekt.Zespolowy.repository.GameRepository;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;
import pl.ug.Projekt.Zespolowy.utility.FileUploadUtil;

import java.io.IOException;
import java.util.List;


@Controller
public class GameController {

    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final ConsoleRepository consoleRepository;

    GameController(GameRepository repository, GenreRepository genreRepository, PublisherRepository publisherRepository,
                   ConsoleRepository consoleRepository){
        this.gameRepository = repository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.consoleRepository = consoleRepository;
    }

    @GetMapping("/games")
    String getGame(Model model){
        model.addAttribute("allGames", gameRepository.findAll());
        return "game-list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/games-admin")
    String getGameAdmin(Model model){
        model.addAttribute("allGames", gameRepository.findAll());
        return "game-list-admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/saveGame")
    public String addGame(ModelMap model) {
        model.addAttribute("newGame", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "save-game";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute("saveGame") Game game, ModelMap model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        game.setPathCover("/covers/"+filename);
        String uploadDir = "src/main/resources/static/covers";
        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        gameRepository.save(game);

        model.addAttribute("allGames", gameRepository.findAll());
        return "redirect:/games-admin/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/editGame/{id}")
    public String editGame(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedGame", gameRepository.getById(id));
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("publishers", publisherRepository.findAll());
        return "edit-game";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/editGame")
    public String editGame(@ModelAttribute("editedGame") Game game, ModelMap model){
        gameRepository.save(game);
        //model.addAttribute("allGames", gameRepository.findAll());
        return "redirect:/games-admin/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteGame/{id}")
    public String deleteGame(@PathVariable("id") long id, ModelMap model) {
        gameRepository.deleteById(id);
        model.addAttribute("allGames", gameRepository.findAll());
        return "redirect:/games-admin/";
    }

}

