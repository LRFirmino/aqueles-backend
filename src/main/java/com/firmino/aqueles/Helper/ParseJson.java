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

    private static final ObjectMapper mapper = new ObjectMapper();


    public static Quote toMap(String filePath){
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(new File(filePath), Quote.class);
        }


        catch (IOException e){
            throw new JsonParsingException("Failed to read or parse JSON file, error: " + e);
        }

    }

    public static void toJson(Quote quote){
        try {
            mapper.writeValue(new File("todayQuote.json"), quote);
        }
        catch (IOException e){
            throw new JsonParsingException("Failed to read or parse JSON file, error: " + e);
        }
    }

}



