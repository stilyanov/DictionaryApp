package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordInfoDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Transactional
    public String loggedIn(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        Map<LanguageEnum, List<Word>> allWords = wordService.findAllByLanguage();

        List<WordInfoDTO> germans = allWords.get(LanguageEnum.GERMAN)
                .stream()
                .map(WordInfoDTO::new)
                .collect(Collectors.toList());

        List<WordInfoDTO> french = allWords.get(LanguageEnum.FRENCH)
                .stream()
                .map(WordInfoDTO::new)
                .collect(Collectors.toList());

        List<WordInfoDTO> italians = allWords.get(LanguageEnum.ITALIAN)
                .stream()
                .map(WordInfoDTO::new)
                .collect(Collectors.toList());

        List<WordInfoDTO> spanish = allWords.get(LanguageEnum.SPANISH)
                .stream()
                .map(WordInfoDTO::new)
                .collect(Collectors.toList());

        model.addAttribute("germansData", germans);
        model.addAttribute("frenchData", french);
        model.addAttribute("italiansData", italians);
        model.addAttribute("spanishData", spanish);

        return "home";
    }

}
