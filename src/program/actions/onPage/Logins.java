package program.actions.onPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.ActionsInput;
import program.actions.Action;
import program.pages.*;
import program.util.Database;
import program.util.Movie;
import program.util.User;
import program.util.dependencies.Credentials;

import java.util.ArrayList;

public class Logins extends Feature implements Action {
    private Credentials credentials;

    // constructor
    public Logins(ActionsInput input) {
        super(input);
        credentials = new Credentials(input.getCredentials());
    }

    @Override
    public void visit(LoggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(UnloggedHomepage page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Login page, ObjectMapper mapper, ObjectNode node, Database database) {
        //Database database = Database.getInstance();
        int index = nameDoesNotExists(credentials, database.getUsersData());
        if (index < 0) {
            node.put("error", "Error");
            node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
            node.set("currentUser", null);
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
        node.set("error", null);
        node.set("currentMoviesList", mapper.valueToTree(
                database.getCurrentPage().getUserMovies()));
        node.set("currentUser", mapper.valueToTree(database.getCurrentUser()));
    }

    @Override
    public void visit(Logout page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Movies page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Register page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(Upgrades page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
    }

    @Override
    public void visit(SeeDetails page, ObjectMapper mapper, ObjectNode node, Database data) {
        node.put("error", "Error");
        node.set("currentMoviesList", mapper.valueToTree(new ArrayList<>()));
        node.set("currentUser", null);
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
