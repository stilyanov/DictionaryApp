package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;
    private final WordRepository wordRepository;

    public LanguageService(LanguageRepository languageRepository, ModelMapper modelMapper, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.wordRepository = wordRepository;
    }

    public boolean addWord(WordAddDTO wordAddDTO) {
        Language map = modelMapper.map(wordAddDTO, Language.class);

        languageRepository.save(map);

        return true;
    }

}
