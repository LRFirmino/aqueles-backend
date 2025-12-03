package com.firmino.aqueles.Service;

import com.firmino.aqueles.Helper.ParseJson;
import com.firmino.aqueles.Model.Quote;
import com.firmino.aqueles.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final ParseJson parser;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository,ParseJson parser){
        this.quoteRepository = quoteRepository;
        this.parser = parser;
    }

    public synchronized Quote getTodayQuote(){
        Instant now = Instant.now();
        ZoneId brZone = ZoneId.of("America/Sao_Paulo");
        LocalDate dateNowBr = ZonedDateTime.ofInstant(now,brZone).toLocalDate();

        if (Files.exists(Path.of("todayQuote.json"))){
            Quote savedQuote = parser.toMap("todayQuote.json");
            if (savedQuote.getChosenDate().equals(dateNowBr)){
                return savedQuote;
            }
        }
        
        Quote newQuote =  quoteRepository.findTop1ByOrderByChosenCountAsc();
        //updating transient field for local json
        newQuote.setChosenDate(dateNowBr);
        //updating selected count
        newQuote.setChosenCount(newQuote.getChosenCount()+1);
        quoteRepository.save(newQuote);
        parser.toJson(newQuote);
        return newQuote;

    }
}
