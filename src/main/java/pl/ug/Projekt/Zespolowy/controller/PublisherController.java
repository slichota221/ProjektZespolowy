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

    @GetMapping("/publishers")
    String getPublishers(Model model){
        model.addAttribute("allPublishers", publisherRepository.findAll());

        return "publisher-list";
    }

    @GetMapping("/publishers/admin")
    String getPublisherAdmin(Model model){
        model.addAttribute("allPublishers", publisherRepository.findAll());

        return "publisher-list-admin";
    }

    @GetMapping("/publisher/save")
    public String addPublisher(Model model){
        model.addAttribute("newPublisher", new Publisher());

        return "save-publisher";
    }

    @PostMapping("/publisher/save")
    public String savePublisher(@ModelAttribute("newPublisher") Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/publishers/admin";
    }

    @GetMapping(value = "/publisher/edit/{id}")
    public String editPublisher(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedPublisher", publisherRepository.getById(id));

        return "edit-publisher";
    }

    @PostMapping("/publisher/edit")
    public String editPublisher(@ModelAttribute("editedGame") Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/publishers/admin";
    }

    @GetMapping("/publisher/delete/{id}")
    public String deletePublisher(@PathVariable("id") long id) {
        publisherRepository.deleteById(id);

        return "redirect:/publishers/admin";
    }

}
