package pl.ug.Projekt.Zespolowy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;

@Controller
public class PublisherController {

    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/web/publishers")
    String getGenres(Model model){
        model.addAttribute("allPublishers", publisherRepository.findAll());

        return "publisher-list";
    }

    @GetMapping("/web/publishers-admin")
    String getGameAdmin(Model model){
        model.addAttribute("allPublishers", publisherRepository.findAll());

        return "publisher-list-admin";
    }

    @GetMapping("/web/savePublisher")
    public String addPublisher(Model model){
        model.addAttribute("newPublisher", new Publisher());

        return "save-publisher";
    }

    @PostMapping("/web/savePublisher")
    public String savePublisher(@ModelAttribute("newPublisher") Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/web/publishers-admin";
    }

    @GetMapping(value = "/web/editPublisher/{id}")
    public String editGame(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedPublisher", publisherRepository.getById(id));

        return "edit-genre";
    }

    @PostMapping("/web/editPublisher")
    public String editGame(@ModelAttribute("editedGame") Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/web/publishers-admin";
    }

    @GetMapping("/web/deletePublisher/{id}")
    public String deleteGame(@PathVariable("id") long id) {
        publisherRepository.deleteById(id);

        return "redirect:/web/publishers-admin";
    }

}
