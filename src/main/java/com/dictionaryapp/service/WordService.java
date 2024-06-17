package com.dictionaryapp.service;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public boolean addWord(WordAddDTO wordAddDTO, Long id) {
        Language language = new Language();
        User byId = this.userRepository.findById(id).get();

        Word map = modelMapper.map(wordAddDTO, Word.class);
        map.setLanguage(language);
        map.setId(byId.getId());

        wordRepository.save(map);
        return true;


    }
}
