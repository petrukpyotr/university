package org.example.lab5;

/**
 * The type Movie.
 */
public class Movie {
    private String director;
    private String title;


    /**
     * Instantiates a new Movie.
     *
     * @param director the director
     * @param title    the title
     */
    public Movie(String director, String title) {
        this.director = director;
        this.title = title;
    }

    /**
     * Gets director.
     *
     * @return the director
     */
    public String getDirector() { return director; }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return title; }
}
