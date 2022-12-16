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
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.pages.Register;
import program.pages.PageFactory;
import program.pages.Page;
import program.util.Database;
import program.util.Movie;
import program.util.User;
import program.util.dependencies.Credentials;

import java.util.ArrayList;

public class Logins extends Feature implements Action {
    private final Credentials credentials;

    // constructor
    public Logins(ActionsInput input) {
        super(input);
        credentials = new Credentials(input.getCredentials());
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
    public void visit(Login page, ObjectNode node, Database database) {
        //Database database = Database.getInstance();
        int index = nameDoesNotExists(credentials, database.getUsersData());
        if (index < 0) {
            OutputError.set(node);
            database.setCurrentPage(PageFactory.createPage(
                    "homepage neautentificat", database));
            return;
        }

        // set user logged and current page
        database.setCurrentUser(database.getUsersData().get(index));
        database.setCurrentPage(PageFactory.createPage(
                "homepage autentificat", database));
        database.getCurrentPage().setCurrentUser(database.getCurrentUser());

        // set user movie list
        database.setUserMovies(new ArrayList<>(database.getMoviesData()));
        // Delete banned movies
        database.getUserMovies().removeIf(
                (movie) -> movieBanned(database.getCurrentPage(), movie));

        // set output
        StandardOutput.set(node, database.getCurrentPage());
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
        OutputError.set(node);
    }

    @Override
    public void apply(Database database, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        // visit page
        database.getCurrentPage().accept(this, mapper, node, database);
        output.add(node);
    }

    private int nameDoesNotExists(Credentials credentials, ArrayList<User> users) {
        // check if name exists in database
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getCredentials().getName().equals(credentials.getName())
                && user.getCredentials().getPassword().equals(credentials.getPassword())) {
                return i;
            }
        }
        return -1;
    }
    private boolean movieBanned(Page page, Movie movie) {
        // check if user country belongs to banned countries of movie
        return movie.getCountriesBanned().contains(
                page.getCurrentUser().getCredentials().getCountry());
    }
}
