package pl.ug.Projekt.Zespolowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import pl.ug.Projekt.Zespolowy.dto.UserRequestDTO;
import pl.ug.Projekt.Zespolowy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserRequestDTO());
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(UserRequestDTO userRequestDTO) {
        userService.register(userRequestDTO);
        return "register_success";
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }
}
