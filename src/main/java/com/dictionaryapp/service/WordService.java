package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        User user = userId.get();

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
        word.setAddedBy(user);

        wordRepository.save(word);

        return true;
    }

    @Transactional
    public Map<LanguageEnum, List<Word>> findAllByLanguage() {
        Map<LanguageEnum, List<Word>> result = new HashMap<>();

        List<Language> allLanguages = languageRepository.findAll();

        for (Language language : allLanguages) {
            List<Word> words = wordRepository.findAllByLanguage(language);

            result.put(language.getName(), words);
        }

        return result;
    }

    public int findAllWordsCount() {
        return this.wordRepository.findAll().size();
    }

    @Transactional
    public void removeWord(long wordId) {
        wordRepository.deleteById(wordId);
    }

    @Transactional
    public void removeAllWords() {
        this.wordRepository.deleteAll();
    }
}
