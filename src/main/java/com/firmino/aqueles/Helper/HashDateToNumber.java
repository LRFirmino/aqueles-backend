package com.firmino.aqueles.Helper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HashDateToNumber {
    private static final Map<Character, Integer> cardCounts = Map.ofEntries(
            Map.entry('A', 1),
            Map.entry('B',2),
            Map.entry('C',3)
    );

    public static int hashDateToNumber(LocalDate date){
        return 1;
    }
}
