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
import program.pages.Logout;
import program.pages.Login;
import program.pages.Register;
import program.pages.Movies;
import program.pages.SeeDetails;
import program.pages.Upgrades;
import program.pages.PageFactory;
import program.util.Database;
import program.util.Movie;
import program.util.User;
import program.util.dependencies.Credentials;
import java.util.ArrayList;

public class Registers extends Feature implements Action {
    private final Credentials credentials;

    // contructor
    public Registers(ActionsInput input) {
        super(input);
        this.credentials = new Credentials(input.getCredentials());
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database database) {
        //Database database = Database.getInstance();
        if (nameAlreadyExists(credentials, database.getUsersData())) {
            OutputError.set(node);
            database.setCurrentPage(PageFactory.createPage("homepage neautentificat", database));
            return;
        }

        // save new user in database
        database.getUsersData().add(new User(credentials));
        int size = database.getUsersData().size();
        database.setCurrentUser(database.getUsersData().get(size - 1));
        database.getCurrentPage().setCurrentUser(database.getCurrentUser());
        // set new page
        database.setCurrentPage(PageFactory.createPage("homepage autentificat", database));

        // set user movie list
        database.setUserMovies(new ArrayList<>(database.getMoviesData()));
        // remove banned movies
        database.getUserMovies().removeIf(
                (movie) -> movieBanned(database.getCurrentUser(), movie));

        // save output
        StandardOutput.set(node, database.getCurrentPage());
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database database) {
        OutputError.set(node);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database database) {
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
    private boolean nameAlreadyExists(Credentials credentials, ArrayList<User> users) {
        // check if name already exists in database
        for (User user: users) {
            if (user.getCredentials().getName().equals(credentials.getName())) {
                return true;
            }
        }
        return false;
    }
    private boolean movieBanned(User user, Movie movie) {
        // check if user country belongs to banned countries of movie
        return movie.getCountriesBanned().contains(
                user.getCredentials().getCountry());
    }
}
