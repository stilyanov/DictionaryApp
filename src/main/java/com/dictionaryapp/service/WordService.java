package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;


@Service
public class WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final LanguageService languageService;
    private final UserService userService;

    public WordService(WordRepository wordRepository, UserRepository userRepository, LanguageRepository languageRepository, LanguageService languageService, UserService userService) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.languageService = languageService;
        this.userService = userService;
    }

    public void addWord(WordAddDTO wordAddDTO, Long id) {
        Language language = languageService.findLanguage(wordAddDTO.getLanguage());
        User userById = userRepository.findById(id).orElse(null);

    }
}
