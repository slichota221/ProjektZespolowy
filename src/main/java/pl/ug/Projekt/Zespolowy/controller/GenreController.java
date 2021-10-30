package pl.ug.Projekt.Zespolowy.controller;


import org.springframework.web.bind.annotation.*;
import pl.ug.Projekt.Zespolowy.exception.GenreNotFoundException;
import pl.ug.Projekt.Zespolowy.repository.GenreRepository;
import pl.ug.Projekt.Zespolowy.domain.Genre;

import java.util.List;


@RestController
public class GenreController {

    private final GenreRepository repository;

    GenreController(GenreRepository repository){
        this.repository = repository;
    }

    @GetMapping("/genres")
    List<Genre> all(){
        return repository.findAll();
    }

    @PostMapping("/genres")
    Genre newGenre(@RequestBody Genre newGenre){
        return repository.save(newGenre);
    }

    @GetMapping("/genre/{id}")
    Genre one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    @PutMapping("/genre/{id}")
    Genre replaceGenre(@RequestBody Genre newGenre, @PathVariable Long id){

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newGenre.getName());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newGenre.setID(id);
                    return repository.save(newGenre);
                });
    }
    @DeleteMapping("/genre/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
