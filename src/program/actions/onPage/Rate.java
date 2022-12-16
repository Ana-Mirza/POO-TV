package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.actions.output.StandardOutput;
import program.pages.LoggedHomepage;
import program.pages.UnloggedHomepage;
import program.pages.Login;
import program.pages.Logout;
import program.pages.Register;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.util.Database;
import program.util.Movie;

import java.util.ArrayList;

public class Rate extends Feature implements Action {
    private final int rating;
    public Rate(ActionsInput input) {
        super(input);
        rating = input.getRate();
    }

    @Override
    public void visit(LoggedHomepage page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Register page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Upgrades page, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectNode node, Database data) {
        // check if movie was watched and if rating is valid
        Movie movie = page.getUserMovies().get(0);
        if (!watchedMovie(movie, page.getCurrentUser().getWatchedMovies())
            || rating < 1 || rating > 5) {
            OutputError.set(node);
            return;
        }

        // rate movie
        movie.setRatingSum(movie.getRatingSum() + rating);
        movie.setNumRatings(movie.getNumRatings() + 1);
        movie.setRating((movie.getRatingSum() / (double) movie.getNumRatings()));
        page.getCurrentUser().getRatedMovies().add(movie);
        // save output
        StandardOutput.set(node, page);
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, mapper, node, data);
        output.add(node);
    }

    private boolean watchedMovie(Movie movie, ArrayList<Movie> movies) {
        for (Movie currentMovie: movies) {
            if (currentMovie.equals(movie)) {
                return true;
            }
        }
        return false;
    }
}
