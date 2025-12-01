package com.firmino.aqueles.Service;

import com.firmino.aqueles.Helper.ParseJson;
import com.firmino.aqueles.Model.Quote;
import com.firmino.aqueles.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;


@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final ParseJson parser;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository,ParseJson parser){
        this.quoteRepository = quoteRepository;
        this.parser = parser;
    }

    public Quote getTodayQuote(){
        LocalDate dateNow = LocalDate.now();

        if (Files.exists(Path.of("todayQuote.json"))){
            Quote savedQuote = parser.toMap("todayQuote.json");
            if (savedQuote.getChosenDate().equals(dateNow)){
                return savedQuote;
            }
        }

        Quote newQuote =  quoteRepository.findTop1ByOrderByChosenCountAsc();
        //updating transient field for local json
        newQuote.setChosenDate(LocalDate.now());
        //updating selected count
        newQuote.setChosenCount(newQuote.getChosenCount()+1);
        quoteRepository.save(newQuote);
        parser.toJson(newQuote);
        return newQuote;

    }
}
