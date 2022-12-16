package program.actions.changePage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.actions.output.OutputError;
import program.actions.output.StandardOutput;
import program.pages.UnloggedHomepage;
import program.pages.Login;
import program.pages.LoggedHomepage;
import program.pages.Logout;
import program.pages.Register;
import program.pages.Movies;
import program.pages.Upgrades;
import program.pages.SeeDetails;
import program.pages.PageFactory;
import program.pages.Page;
import program.util.Database;
import program.util.User;

import java.util.ArrayList;

public class ChangePage implements Action {
    private final String page;
    private final String movie;
    private boolean displayOutput;

    // constructors
    public ChangePage(ActionsInput input) {
        page = input.getPage();
        movie = input.getMovie();
        displayOutput = false;
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database data) {
        StandardOutput.set(node, page);
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
        StandardOutput.set(node, page);
        displayOutput = true;
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        // set movie available
        page.getUserMovies().removeIf((movie) -> !movie.getName().equals(this.movie));
        displayOutput = true;

        // check if movie was found
        if (page.getUserMovies().size() == 0) {
            // return to movies page if movie was not found
            data.setCurrentPage(PageFactory.createPage("movies", data));
            // set error output
            OutputError.set(node);
            return;
        }

        // set output for movie
        StandardOutput.set(node, page);
    }

    public void apply(Database database, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        // check if page is available
        if (pageNotAvailable(database)) {
            OutputError.set(node);
            output.add(node);
            return;
        }
        // set new current page
        database.setCurrentPage(PageFactory.createPage(page, database));

        // update current page
        database.getCurrentPage().accept(this, mapper, node, database);
        if (displayOutput) {
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
