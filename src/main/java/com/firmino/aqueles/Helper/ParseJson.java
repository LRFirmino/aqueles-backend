package com.firmino.aqueles.Helper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firmino.aqueles.ErrorHandler.JsonParsingException;
import com.firmino.aqueles.Model.Quote;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class ParseJson {

    private final ObjectMapper mapper;

    @Autowired
    public ParseJson(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Quote toMap(String filePath){
        try {
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(new File(filePath), Quote.class);
        }


        catch (IOException e){
            throw new JsonParsingException("Failed to read or parse JSON file, error: " + e);
        }

    }

    public void toJson(Quote quote){
        try {
            mapper.writeValue(new File("todayQuote.json"), quote);
        }
        catch (IOException e){
            throw new JsonParsingException("Failed to read or parse JSON file, error: " + e);
        }
    }

}



