package pl.ug.Projekt.Zespolowy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;

@Controller
public class PublisherController {

    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }


    @GetMapping("/savePublisher")
    public String addPublisher(Model model){
        model.addAttribute("newPublisher", new Publisher());
        return "save-publisher";
    }

    @PostMapping("/savePublisher")
    public String savePublisher(@ModelAttribute("newPublisher") Publisher publisher, ModelMap model){
        publisherRepository.save(publisher);
        model.addAttribute("newPublisher", publisherRepository.findAll());
        return "index";
    }
}
