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
import program.util.Database;

import java.util.ArrayList;

public class Search extends Feature implements Action {
    private final String startsWith;
    private Database database;

    // constructor
    public Search(ActionsInput input) {
        super(input);
        startsWith = input.getStartsWith();
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
        // set user movies
        page.setUserMovies(new ArrayList<>(database.getUserMovies()));
        page.getUserMovies().removeIf((movie) -> !movie.getName().startsWith(startsWith));

        // save output
        StandardOutput.set(node, page);
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
        OutputError.set(node);
    }

    @Override
    public void apply(Database data, ArrayNode output) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        // vi
        this.database = data;
        data.getCurrentPage().accept(this, node);
        output.add(node);
    }
}
