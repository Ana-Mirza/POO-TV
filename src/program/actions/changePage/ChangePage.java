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

public final class ChangePage implements Action {
    private final String page;
    private final String movie;
    private boolean displayOutput;

    // constructors
    public ChangePage(final ActionsInput input) {
        page = input.getPage();
        movie = input.getMovie();
        displayOutput = false;
    }

    @Override
    public void visit(final LoggedHomepage page,
                      final ObjectNode node, final Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(final UnloggedHomepage page,
                      final ObjectNode node, final Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(final Login page,
                      final ObjectNode node, final Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(final Logout page,
                      final ObjectNode node, final Database data) {
        // update current page
        data.setCurrentPage(PageFactory.createPage("homepage neautentificat", data));
        data.setUserMovies(new ArrayList<>());
        data.setCurrentUser(new User());
    }

    @Override
    public void visit(final Movies page,
                      final ObjectNode node, final Database database) {
        // set output
        StandardOutput.set(node, page);
        displayOutput = true;
    }

    @Override
    public void visit(final Register page,
                      final ObjectNode node, final Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(final Upgrades page,
                      final ObjectNode node, final Database data) {
        StandardOutput.set(node, page);
    }

    @Override
    public void visit(final SeeDetails page,
                      final ObjectNode node, final Database data) {
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

    public void apply(final Database database, final ArrayNode output) {
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

    private boolean pageNotAvailable(final Database data) {
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
