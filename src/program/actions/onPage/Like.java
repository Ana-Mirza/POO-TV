package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.actions.output.StandardOutput;
import program.pages.*;
import program.util.Database;
import program.util.Movie;

import java.util.ArrayList;

public class Like extends Feature implements Action {
    public Like(ActionsInput input) {
        super(input);
    }

    @Override
    public void visit(LoggedHomepage page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(Register page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(Upgrades page, ObjectNode node) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectNode node) {
        // check if watched movie
        Movie movie = page.getUserMovies().get(0);
        if (!watchedMovie(movie, page.getCurrentUser().getWatchedMovies())) {
            OutputError.set(node);
            return;
        }

        // like movie
        page.getCurrentUser().getLikedMovies().add(movie);
        movie.setNumLikes(movie.getNumLikes() + 1);
        // save output
        StandardOutput.set(node, page);
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, node);
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
