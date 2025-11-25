package com.firmino.aqueles.Service;

import com.firmino.aqueles.Helper.ParseJson;
import com.firmino.aqueles.Model.Quote;
import com.firmino.aqueles.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class QuoteService {

    private QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    public Quote getTodayQuote(){
        LocalDate dateNow = LocalDate.now();
        Quote savedQuote = ParseJson.toMap("todayQuote.json");

        if (savedQuote.getChosenDate().equals(dateNow)){
            return savedQuote;
        }
        Quote newQuote =  quoteRepository.findTop1ByOrderByChosenCountAsc();
        //updating transient field for local json
        newQuote.setChosenDate(LocalDate.now());
        //updating selected count
        newQuote.setChosenCount(newQuote.getChosenCount()+1);
        quoteRepository.save(newQuote);
        ParseJson.toJson(newQuote);
        return newQuote;
    }
}
