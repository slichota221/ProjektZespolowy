package pl.ug.Projekt.Zespolowy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ug.Projekt.Zespolowy.domain.Genre;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;
import pl.ug.Projekt.Zespolowy.utility.FileUploadUtil;

import java.io.IOException;

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
    public String savePublisher(@ModelAttribute("newPublisher") Publisher publisher, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        publisher.setPathCover("/publishers/"+filename);
        String uploadDir = "src/main/resources/static/publishers";
        FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
        publisherRepository.save(publisher);

        //model.addAttribute("allGames", gameRepository.findAll());
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
