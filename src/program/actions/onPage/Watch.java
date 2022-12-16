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

public class Watch extends Feature implements Action {
    public Watch(ActionsInput input) {
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
        // check if purchased movie
        Movie movie = page.getUserMovies().get(0);
        if (!purchasedMovie(movie, page.getCurrentUser().getPurchasedMovies())) {
            OutputError.set(node);
            return;
        }

        // watch movie
        page.getCurrentUser().getWatchedMovies().add(movie);
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

    private boolean purchasedMovie(Movie movie, ArrayList<Movie> movies) {
        for (Movie currentMovie: movies) {
            if (currentMovie.equals(movie)) {
                return true;
            }
        }
        return false;
    }
}
