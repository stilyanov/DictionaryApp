package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final WordService wordService;
    private final UserSession userSession;
    private final WordRepository wordRepository;

    public WordController(WordService wordService, UserSession userSession, WordRepository wordRepository) {
        this.wordService = wordService;
        this.userSession = userSession;
        this.wordRepository = wordRepository;
    }

    @ModelAttribute("languages")
    public LanguageEnum[] languages() {
        return LanguageEnum.values();
    }

    @ModelAttribute("wordData")
    public WordAddDTO getAddWordData() {
        return new WordAddDTO();
    }

    @GetMapping("/words")
    public String viewAddWord() {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        return "word-add";
    }

    @PostMapping("/words")
    public String addWord(@Valid WordAddDTO wordAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordData", wordAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordData", bindingResult);

            return "redirect:/words";
        }

        boolean success = wordService.create(wordAddDTO);

        if (!success) {
            redirectAttributes.addFlashAttribute("wordData", wordAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordData", bindingResult);

            return "redirect:/words";
        }

        return "redirect:/home";

    }

}
