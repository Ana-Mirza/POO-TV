package program.util.dependencies;

import fileio.ContainsInput;
import program.pages.Page;
import program.util.Movie;

import java.util.ArrayList;

public class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genres;

    // constructor
    public Contains(ContainsInput input) {
        if (input.getActors() != null) {
            actors = new ArrayList<>(input.getActors());
        }
        if (input.getGenre() != null) {
            genres = new ArrayList<>(input.getGenre());
        }
    }

    // getters and setters
    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    // filter method
    public static void filter(Page page, Contains contains) {
        if (contains == null)
            return;

        // apply actor filter if available
        if (contains.getActors() != null) {
            for (String actor: contains.getActors()) {
                page.getUserMovies().removeIf((movie) ->
                        !movie.getActors().contains(actor));
            }
        }
        // apply genre filter if available
        if (contains.getGenres() != null) {
            for (String genre: contains.getGenres()) {
                page.getUserMovies().removeIf((movie) ->
                        !movie.getGenres().contains(genre));
            }
        }
    }

    @Override
    public String toString() {
        return "Contains{" +
                "actors=" + actors +
                ", genres=" + genres +
                '}';
    }
}
