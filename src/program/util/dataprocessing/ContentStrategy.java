package program.util.dataprocessing;

import program.pages.Page;
import program.util.dependencies.Contains;
import program.util.dependencies.Filters;

public class ContentStrategy implements FilterStrategy{
    @Override
    public void filter(Filters input, Page page) {
        Contains contains = input.getContains();

        // check if contains filter exists
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
}
