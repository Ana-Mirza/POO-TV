package program.util.dependencies;

import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;
import fileio.SortInput;
import program.pages.Page;
import program.util.Movie;

import java.util.Collections;
import java.util.Comparator;

public class Sort {
    private String rating;
    private String duration;

    // constructor
    public Sort(SortInput input) {
        rating = input.getRating();
        duration = input.getDuration();
    }

    // getters and setters

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static void movies(Page page, Sort sort) {
        if (sort == null) {
            return;
        }

        // sort based on rating first, then by duration
        if (sort.getRating().equals("increasing")
            && sort.getDuration() == null) {
            Collections.sort(page.getUserMovies(),
                    Comparator.comparingDouble(Movie::getRating));
        } else if (sort.getDuration().equals("decreasing")
                && sort.getRating().equals("decreasing")) {
            Collections.sort(page.getUserMovies(),
                    Comparator.comparingInt(Movie::getDuration).reversed().thenComparing(
                            Comparator.comparingDouble(Movie::getRating).reversed()));
        } else if (sort.getDuration().equals("increasing")
                && sort.getRating().equals("increasing")) {
            Collections.sort(page.getUserMovies(),
                    Comparator.comparingInt(Movie::getDuration).thenComparing(
                            Comparator.comparingDouble(Movie::getRating)));
        } else if (sort.getDuration().equals("increasing")
                && sort.getRating().equals("decreasing")) {
            Collections.sort(page.getUserMovies(),
                    Comparator.comparingInt(Movie::getDuration).thenComparing(
                            Comparator.comparingDouble(Movie::getRating).reversed()));
        } else if (sort.getDuration().equals("decreasing")
                && sort.getRating().equals("increasing")) {
            Collections.sort(page.getUserMovies(),
                    Comparator.comparingInt(Movie::getDuration).reversed().thenComparing(
                            Comparator.comparingDouble(Movie::getRating)));
        }
    }

    @Override
    public String toString() {
        return "Sort{" +
                "rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
