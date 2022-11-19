package br.maciel.utilities;

public class NamesHandler {
    private static NamesHandler namesHandler;

    public static NamesHandler getInstance() {
        if (namesHandler == null) namesHandler = new NamesHandler();
        return namesHandler;
    }

    public String capitalize(String text) {
        String firstLetter = text.substring(0, 1);
        String remainingLetters = text.substring(1);
        return firstLetter.toUpperCase() + remainingLetters;
    }

    public String nameFromId(String idName) {
        idName = idName.toLowerCase();
        String[] words = idName.split("_");
        StringBuilder name = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            name.append(" ").append(words[i]);
        }
        return name.toString();
    }

    private NamesHandler() {
        super();
    }
}
