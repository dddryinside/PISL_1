package com.chernikov.pisl_1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsService {
    public static Map<String, List<String>> processWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        String[] words = input.split("\\s+");

        Map<String, List<String>> categorizedWords = new HashMap<>();
        categorizedWords.put("a", new ArrayList<>());
        categorizedWords.put("b", new ArrayList<>());
        categorizedWords.put("c", new ArrayList<>());
        categorizedWords.put("other", new ArrayList<>());

        for (String w : words) {
            if (w.toLowerCase().startsWith("a")) {
                categorizedWords.get("a").add(w);
            } else if (w.toLowerCase().startsWith("b")) {
                categorizedWords.get("b").add(w);
            } else if (w.toLowerCase().startsWith("c")) {
                categorizedWords.get("c").add(w);
            } else {
                categorizedWords.get("other").add(w);
            }
        }

        return categorizedWords;
    }
}
