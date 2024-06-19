package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final UserSession userSession;

    public WordService(WordRepository wordRepository, UserRepository userRepository, LanguageRepository languageRepository, UserSession userSession) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.userSession = userSession;
    }


    public boolean create(WordAddDTO wordAddDTO) {
        if (!userSession.isUserLoggedIn()) {
            return false;
        }

        Optional<User> userId = userRepository.findById(userSession.id());

        if (userId.isEmpty()) {
            return false;
        }

        Optional<Language> byName = languageRepository.findByName(wordAddDTO.getLanguage());

        if (byName.isEmpty()) {
            return false;
        }

        Word word = new Word();
        word.setTerm(wordAddDTO.getTerm());
        word.setTranslation(wordAddDTO.getTranslation());
        word.setExample(wordAddDTO.getExample());
        word.setInputDate(wordAddDTO.getInputDate());
        word.setLanguage(byName.get());
        word.setAddedBy(userId.get());

        wordRepository.save(word);

        return true;
    }
}
