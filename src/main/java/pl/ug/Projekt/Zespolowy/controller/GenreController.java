package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.utility.FileUploadUtil;

import java.io.IOException;
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
    String getGenresAdmin(Model model){
        model.addAttribute("allGenres", repository.findAll());
        return "genre-list-admin";
    }

    @GetMapping(value = "/genre/save")
    public String genreForm(Model model) {
        model.addAttribute("Genre", new Genre());

        return "save-genre";
    }
    @PostMapping(value = "/genre/save")
    public String saveGenre(Model model, @ModelAttribute("Genre") Genre genre, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        genre.setPathCover("/genres/"+filename);
        String uploadDir = "src/main/resources/static/genres";
        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        repository.save(genre);

        //model.addAttribute("allGames", gameRepository.findAll());
        return "redirect:/genres/admin";
    }

    @GetMapping(value = "/genre/edit/{id}")
    public String editGenre(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedGenre", repository.getById(id));

        return "edit-genre";
    }

    @PostMapping("/genre/edit")
    public String editGenre(@ModelAttribute("editedGame") Genre genre){
        repository.save(genre);

        return "redirect:/genres/admin";
    }

    @GetMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") long id) {
        repository.deleteById(id);

        return "redirect:/genres/admin";
    }
}
