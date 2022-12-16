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

public class Purchase extends Feature implements Action {
    private int moviePrice;
    public Purchase(ActionsInput input) {
        super(input);
        moviePrice = 2;
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
        // check if user does not have enough tokens
        if ((!page.getCurrentUser().isPremium()
                && page.getCurrentUser().getTokensCount() < moviePrice)
                || (page.getCurrentUser().getNumFreePremiumMovies() == 0
                && page.getCurrentUser().getTokensCount() < moviePrice)) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            return;
        }

        // purchase movie
        Movie movie = page.getUserMovies().get(0);
        page.getCurrentUser().getPurchasedMovies().add(movie);
        // update tokens if user is not premium
        if (page.getCurrentUser().isPremium()
                && page.getCurrentUser().getNumFreePremiumMovies() > 0) {
            int currentFreeMovies = page.getCurrentUser().getNumFreePremiumMovies();
            page.getCurrentUser().setNumFreePremiumMovies(currentFreeMovies - 1);
        } else {
            int currentTokens = page.getCurrentUser().getTokensCount();
            page.getCurrentUser().setTokensCount(currentTokens - moviePrice);
        }

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
}
