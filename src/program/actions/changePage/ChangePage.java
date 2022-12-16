package program.actions.changePage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.onPage.Feature;
import program.pages.*;
import program.util.Database;
import program.util.Movie;
import program.util.User;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class ChangePage implements Action {
    private String page;
    private String movie;
    private boolean hasOutput;

    // constructors
    public ChangePage(ActionsInput input) {
        page = input.getPage();
        movie = input.getMovie();
        hasOutput = false;
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        return;
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        return;
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        return;
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        // update current page
        data.setCurrentPage(PageFactory.createPage("homepage neautentificat", data));
        data.setUserMovies(new ArrayList<>());
        data.setCurrentUser(new User());
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database database) {
        // set output
        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(page.getUserMovies()));
        node.set("currentUser", mapper.valueToTree(page.getCurrentUser()));
        hasOutput = true;
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        return;
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        return;
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        // set movie available
        page.getUserMovies().removeIf((movie) -> !movie.getName().equals(this.movie));
        hasOutput = true;

        // check if movie was found
        if (page.getUserMovies().size() == 0) {
            // return to movies page
            data.setCurrentPage(PageFactory.createPage("movies", data));

            // set output
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            return;
        }

        // set output
        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(page.getUserMovies()));
        node.set("currentUser", mapper.valueToTree(page.getCurrentUser()));
    }

    public void apply(Database database, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        // check if page is available
        if (pageNotAvailable(database)) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<String>()));
            node.set("currentUser", null);
            output.add(node);
            return;
        }
        // set new current page
        database.setCurrentPage(PageFactory.createPage(page, database));

        // update current page
        database.getCurrentPage().accept(this, mapper, node, database);
        if (hasOutput) {
            output.add(node);
        }
    }

    private boolean pageNotAvailable(Database data) {
        Page currentPage = data.getCurrentPage();
        // check available pages
        for (String nextPage: currentPage.getAccesiblePages()) {
            if (nextPage.equals(page)) {
                return false;
            }
        }
        return true;
    }
}
