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

public class Purchase extends Feature implements Action {
    private final int moviePrice;
    public Purchase(ActionsInput input) {
        super(input);
        moviePrice = 2;
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        // check if user does not have enough tokens
        if ((!page.getCurrentUser().isPremium()
                && page.getCurrentUser().getTokensCount() < moviePrice)
                || (page.getCurrentUser().getNumFreePremiumMovies() == 0
                && page.getCurrentUser().getTokensCount() < moviePrice)) {
            OutputError.set(node);
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
}
