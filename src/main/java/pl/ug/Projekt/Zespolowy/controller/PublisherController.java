package pl.ug.Projekt.Zespolowy.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ug.Projekt.Zespolowy.domain.Publisher;
import pl.ug.Projekt.Zespolowy.repository.PublisherRepository;
import pl.ug.Projekt.Zespolowy.service.PublisherService;
import pl.ug.Projekt.Zespolowy.utility.FileUploadUtil;

import java.io.IOException;

@Controller
public class PublisherController {

    private final PublisherRepository publisherRepository;
    private final PublisherService publisherService;

    public PublisherController(PublisherRepository publisherRepository, PublisherService publisherService) {
        this.publisherRepository = publisherRepository;
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    String getPublishers(Model model){
        model.addAttribute("allPublishers", publisherRepository.findAll());

        return "publisher-list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/publishers/admin")
    String getPublisherAdmin(Model model){
        model.addAttribute("allPublishers", publisherService.getAllPublishersForAdminView());

        return "publisher-list-admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/publisher/save")
    public String addPublisher(Model model){
        model.addAttribute("newPublisher", new Publisher());

        return "save-publisher";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/publisher/edit/{id}")
    public String editPublisher(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("editedPublisher", publisherRepository.getById(id));

        return "edit-publisher";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/publisher/edit")
    public String editPublisher(@ModelAttribute("editedGame") Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/publishers/admin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/publisher/delete/{id}")
    public String deletePublisher(@PathVariable("id") long id) {
        publisherRepository.deleteById(id);

        return "redirect:/publishers/admin";
    }

}
