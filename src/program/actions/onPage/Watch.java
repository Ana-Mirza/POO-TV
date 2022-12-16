package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.pages.*;
import program.util.Database;
import program.util.Movie;

import java.util.ArrayList;

public class Watch extends Feature implements Action {
    public Watch(ActionsInput input) {
        super(input);
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        // check if purchased movie
        Movie movie = page.getUserMovies().get(0);
        if (!purchasedMovie(movie, page.getCurrentUser().getPurchasedMovies())) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            return;
        }

        // watch movie
        page.getCurrentUser().getWatchedMovies().add(movie);
        // save output
        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(page.getUserMovies()));
        node.set("currentUser", mapper.valueToTree(page.getCurrentUser()));
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // visit page
        data.getCurrentPage().accept(this, mapper, node, data);
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
