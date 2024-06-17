package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
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
        return "word-add";
    }

    @PostMapping("/words")
    public String addWord(@Valid WordAddDTO wordAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordData", wordAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.wordData", bindingResult);

            return "redirect:/words";
        }

        wordService.addWord(wordAddDTO, wordAddDTO.getId());

        return "redirect:/home";
    }

}
