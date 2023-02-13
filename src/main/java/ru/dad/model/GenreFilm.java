package ru.dad.model;

public enum GenreFilm {
    FANTASY("Фантастика"),
    ACTION("Боевик"),
    THRILLER("Триллер"),
    COMEDY("Комедия"),
    DETECTIVE("Детектив");
    private final String textDisplay;
    GenreFilm(String genreName) {
        this.textDisplay = genreName;
    }
    public String getTextDisplay() {
        return textDisplay;
    }
}
