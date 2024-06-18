package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserSession userSession;
    private final UserService userService;
    private final WordService wordService;

    public HomeController(UserSession userSession, UserService userService, WordService wordService) {
        this.userSession = userSession;
        this.userService = userService;
        this.wordService = wordService;
    }

    @GetMapping("/")
    public String notLoggedIn() {
        if (userSession.isUserLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedIn() {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }


    }

}
