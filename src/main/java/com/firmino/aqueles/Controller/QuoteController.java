package com.firmino.aqueles.Controller;

import com.firmino.aqueles.Model.Quote;
import com.firmino.aqueles.Service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quotes")
@CrossOrigin(origins = "*")
public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    @GetMapping("/todayQuote")
    public ResponseEntity<Quote> getTodayQuote(){
        return ResponseEntity.ok().body(quoteService.getTodayQuote());
    }

}
