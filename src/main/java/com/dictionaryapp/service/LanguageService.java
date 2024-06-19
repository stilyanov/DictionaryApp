package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;


@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;

    public LanguageService(LanguageRepository languageRepository,WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
    }

}
